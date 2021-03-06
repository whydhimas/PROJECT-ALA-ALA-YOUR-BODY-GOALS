package com.example.android.yourbodygoals_apps;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuCalculator extends AppCompatActivity {
    EditText mHeightCalc, mWeightCalc, mAgeCalc;
    Button mCountCalc;
    TextView mResultNumCalc, mResultCalc, mResultMakananMinuman;

    private SQLiteDatabase database;
    private boolean mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_calculator);

        //ngganti warna status bar sama action bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        mHeightCalc = (EditText) findViewById(R.id.et_height_calc);
        mWeightCalc = (EditText) findViewById(R.id.et_weight_calc);
        mAgeCalc = (EditText) findViewById(R.id.et_age_calc);
        mCountCalc = (Button) findViewById(R.id.btn_count_calc);
        mResultNumCalc = (TextView) findViewById(R.id.tv_calc_result_number);
        mResultCalc = (TextView) findViewById(R.id.tv_result_caption);

        mResultMakananMinuman = (TextView) findViewById(R.id.tv_makanan_minuman_inform);

        mCountCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHeight = mHeightCalc.getText().toString();
                String strWeight = mWeightCalc.getText().toString();

                if (strHeight != null && !"".equals(strHeight) && strWeight != null  &&  !"".equals(strWeight)) {
                    float heightValue = Float.parseFloat(strHeight) / 100;
                    float weightValue = Float.parseFloat(strWeight);

                    float bmi = weightValue / (heightValue * heightValue);

                    displayBMI(bmi);
                }
            }
        });

        mResultCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* myDialog = new Dialog(MenuCalculator.this);
                myDialog.setContentView(R.layout.popup_information);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();*/

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuCalculator.this);
                View mView = getLayoutInflater().inflate(R.layout.popup_information, null);
                TextView mResultMnM = (TextView) mView.findViewById(R.id.tv_makanan_minuman_inform);
                mBuilder.setView(mView);

                final AlertDialog dialog = mBuilder.create();

                int usia = Integer.parseInt(mAgeCalc.getText().toString());

                String resultUsia = "";

                if(usia <= 12){
                    resultUsia ="Makanan yang cocok untuk diberikan pada usia "+usia+" tahun harusnya menyediakan proteion, kalsium, zat besi, dan vitamin yang penting untuk pertumbuhan dan perkembangan anak. Kalau kelengkapa gizi ini kurang, pertumbuhan anak-anak bisa terhambat yang dapat mempengaruhi perkembangan dan kemampuan mental dan motorik anak. Nutrisi ini terdapat dalam kelompok makanan dalam biji-bijian, buah-buahan, sayuran, dan protein. \n\nMinuman yang disarankan adalah susu sapi murni yang penting untuk membangun tulang dan otot usia anak-anak. Susu bisa dikonsumsi saat sarapan, disajikan dengan sereal atau cookies, atau bisa juga mencampur dengan buah-buahan.\n";
                } else if(usia <= 19 ){
                    resultUsia ="-";
                } else if (usia <= 29){
                    resultUsia ="Pada usia "+usia+", tubuh anda akan berhenti membangun massa tulang pada usia sekitar 30 tahun. Oleh sebab itu di usia 20an, anda membutuhkan asupan kalsium tinggi untuk pembetukan tulang yang kokoh. Pada usia ini, tubuh anda membutuhkan sekitar 1000mg kalsium per hari, dan anda bisa mendapatkan kalsium ini dengan mengonsumsi keju, tahu, ikan salmon, dan sayuran hijau, terutama kangkung. \n\nMinuman yang disarankan adalah minum susu pada saat sarapan, dan minum air mineral yang banyak saat beraktifitas sehingga suhu tubuh terjaga dan terhindari dari dehidrasi.\n";
                } else if (usia <= 39){
                    resultUsia ="Makanan yang cocok untuk diberikan pada usia "+usia+" tahun adalah biji-bijian seperti bunga matahari yang mengandung asam lemak omega-3 tinggi yang dapat menurunkan resiko peradangan sendi yang menyerang umur 30th an dengan membatu melumasi sendi. Juga bisa mendapatkan lemak sehat pada kacang-kacangan, ikan kembung, dan ikan teri. \n\nMinuman yang disarankan adalah susu skim bebas lemak dan kolesterol, yang menjadi minuman alternatif yang lebih sehat daripada susu biasa. Susu skim juga sumber kalsium yang baik untuk tubuh dan membantu dalam menjaga kesehatan tulang.\n";
                } else if (usia <= 49){
                    resultUsia ="Pada usia "+usia+" tahun, metabolisme tubuh pada usia ini akan menurun, maka makanan yang disarankan dalam usia "+usia+" adalah makanan yang mengandung serat tinggi, contohnya kacang-kacangan yang memberikan 15 gram serat dalam satu porsi. Mengindari makanan siap saji, kurangi garam, kurangi gula, kurangi kafein, dan bisa untuk mengonsumsi suplemen tambahan yang mengandung nutrisi tinggi sebagai penunjang kesehatan tubuh sehari-hari. \n\nMinuman yang disarankan adalah perbanyak air mineral, setidaknya 8liter perhari untuk dapat melindungi tubuh dari dehidrasi, kelelahan dan sebagai menjaga stamina tubuh agar tetap stabil, sehingga mengindari tubuh agar tidak mudah jatuh sakit, serta rutin untuk minum jus buah yang gulanya dikurangi.\n";
                } else if (usia <= 59){
                    resultUsia ="Makanan yang harus dikonsumsi pada usia "+usia+" tahun adalah gandum utuh. Gandum utuh adalah makanan pertama yang wajib dikonsumsi ketika usia sudah mencapai 50 tahun. Fungsinya adalah melancarkan pencernaan karena kandungan seratnya yang tinggi. Selain gandum ada kacang-kacangan, termasuk di dalamnya kacang merah, kacang hitam, dan jenis kacang-kacangan lainnya. Karena kacang-kacangan mengandung serat, protein, dan kalium yang bekerja melawan sodium - biasanya memicu peningkatan tekanan darah. Karena kandungan proteinnya yang tinggi, kacang-kacangan bisa dibilang sebagai pengganti daging bagi vegetarian. \n\nMinuman yang disarankan adalah kopi,  sebab minuman ini juga dianjurkan agar tetap dikonsumsi di usia 50 tahun ke atas. Namun jangan lupa untuk membatasi konsumsinya sebanyak satu cangkir saja setiap hari. Selain kopi juga adalah air mineral.\n";
                } else if (usia <= 71){
                    resultUsia ="Makanan yang harus dikonsumsi di usia "+usia+" tahun adalah kuncinya pada protein. Rentang usia 60 hingga 75 tahun, membutuhkan nutrisi yang tepat melalui protein. Asupan protein harus lebih banyak, sebab, dapat menurunkan risiko berkurangnya massa otot. Lansia, membutuhkan protein rendah lemak seperti ikan, telur dan tempe. Tidak hanya itu, komponen zat gizi mikro juga dibutuhkan agar menjaga sistem imunnya tetap baik. \n\nMinuman yang disarankan untuk rentang usia ini adalah seperti jus dan smoothies. Adanya jus dan smoothies ini dapat membantu agar lansia tetap terpenuhi kebutuhan nutrisinya. Nah, mulai sekarang saatnya memasukkan ragam jus buah untuk lansia dalam konsumsinya.\n";
                } else {
                    resultUsia ="Maaf, usia yang anda masukkan diluar rata-rata usia manusia di Indonesia.\n";
                }
                mResultMnM.setText(resultUsia);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.rumusBMI) {
            buildDialog(0, "Dasar rumus perhitungan Indeks Massa Tubuh atau BMI \nBMI = Berat Badan / (Tinggi Badan * Tinggi Badan");
        }
        return super.onOptionsItemSelected(item);
    }

    private void buildDialog(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rumus BMI");
        builder.setMessage(type);
        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        mResultNumCalc.setText(Float.toString(bmi));
        mResultCalc.setText(bmiLabel);
    }

}
