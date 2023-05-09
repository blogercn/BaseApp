package com.hxdl.coceweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.hxdl.coceweb.util.Util;
import com.vinsonguo.klinelib.chart.KLineView;
import com.vinsonguo.klinelib.chart.TimeLineView;
import com.vinsonguo.klinelib.model.HisData;

import java.util.List;

import static com.tencent.bugly.crashreport.CrashReport.getContext;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //TextView tv = findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());

        final List<HisData> hisData = Util.get1Day(this);
        TimeLineView mTimeLineView = this.findViewById(R.id.timeline);//new TimeLineView(getContext());  //初始化分时图
        mTimeLineView.setDateFormat("HH:mm");  // 设置x轴时间的格式
        mTimeLineView.setLastClose(hisData.get(0).getClose());  // 设置昨收价
        mTimeLineView.initData(hisData);  // 初始化图表数据

        KLineView mKLineView = this.findViewById(R.id.kline);
        mKLineView.showKdj();
        mKLineView.showVolume();
        final List<HisData> hisData2 = Util.getK(this, 1);
        mKLineView.initData(hisData2);
        mKLineView.setLimitLine();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
