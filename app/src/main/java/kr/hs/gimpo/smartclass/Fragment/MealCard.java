package kr.hs.gimpo.smartclass.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kr.hs.gimpo.smartclass.R;


public class MealCard extends Fragment {
    
    public MealCard() {
    
    }
    
    String date = new SimpleDateFormat("yyyy-MM-dd-HH", Locale.getDefault()).format(Calendar.getInstance().getTime());
    
    // 탑재된 액티비티에 따라 표시되는 레이아웃이 달라집니다!
    // 0: 에러, 1: 급식, 2: 메인
    int mode = 2;
    
    boolean isInit = false;
    
    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        
        mode = 1;
        
        String temp = new SimpleDateFormat("yyyy-MM-dd-HH", Locale.getDefault()).format(Calendar.getInstance().getTime());
        
        if(args != null) {
            date = args.getString("date", temp);
        } else {
            date = temp;
        }
        if(isInit) {
            initMealDataListener(getView());
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meal_card, container, false);
        isInit = true;
        initMealDataListener(view);
        
        return view;
    }
    
    DatabaseReference mDatabase;
    
    public void initMealDataListener(final View view) {
        mDatabase = FirebaseDatabase.getInstance().getReference("test");
        
        mDatabase.child("mealDataFormat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Year, Month, Day, Hour!
                String[] ymdh = date.split("-");
                
                String data = dataSnapshot.child("mealData").child(ymdh[0]).child(ymdh[1]).child(String.valueOf(Integer.parseInt(ymdh[2]) - 1)).child(String.valueOf(Integer.parseInt(ymdh[3]) < 14 ? 0 : 1)).getValue(String.class);
    
                TextView mealData = view.findViewById(R.id.card_meal_data);
                
                data = (data != null && data.compareTo("등록된 식단 정보가 없습니다.") != 0)? data: getResources().getString(R.string.meal_card_data_null);
                
                mealData.setText(data);
                
                TextView mealDate = view.findViewById(R.id.card_meal_date);
                
                TextView mealDOW = view.findViewById(R.id.card_meal_dow);
                
                if(mode == 2) {
                    String temp = String.format(getResources().getString(R.string.date_format), ymdh[0], ymdh[1], ymdh[2]);
                    mealDate.setVisibility(TextView.VISIBLE);
                    mealDate.setText(temp);
                    
                    temp = new SimpleDateFormat("EEE", Locale.getDefault()).format(Calendar.getInstance().getTime());
                    temp = "[ " + temp + " ]";
                    
                    mealDOW.setVisibility(TextView.VISIBLE);
                    mealDOW.setText(temp);
                } else {
                    mealDate.setVisibility(TextView.GONE);
                    mealDOW.setVisibility(TextView.GONE);
                }
                
                TextView mealType = view.findViewById(R.id.card_meal_time);
    
                if(Integer.parseInt(ymdh[3]) < 14) {
                    mealType.setText(getResources().getString(R.string.lunch));
                } else {
                    mealType.setText(getResources().getString(R.string.dinner));
                }
            }
    
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
        
            }
        });
    }
}
