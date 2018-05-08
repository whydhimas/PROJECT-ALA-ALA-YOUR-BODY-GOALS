package com.example.android.yourbodygoals_apps;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabProfile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference rootRef, demoRef;

    TextView mName, mAge, mWeight, mHeight, mResultNumCalc, mResultCalc, mResultMakananMinuman;

    String mStringData1, mStringData2, mStringData3, mStringData4;

    View v;
    public TabProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static TabProfile newInstance(String param1, String param2) {
        TabProfile fragment = new TabProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tab_profile, container, false);

        mName = (TextView)view.findViewById(R.id.tv_profile_name);
        mAge = (TextView)view.findViewById(R.id.tv_profile_age);
        mHeight = (TextView)view.findViewById(R.id.tv_profile_height);
        mWeight = (TextView)view.findViewById(R.id.tv_profile_weight);
        mResultNumCalc = (TextView)view.findViewById(R.id.tv_profile_bmi);
        mResultCalc = (TextView)view.findViewById(R.id.tv_profile_result_capt);

        mResultMakananMinuman = (TextView)view.findViewById(R.id.tv_makanan_minuman_inform);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("Login");

        ValueEventListener evl = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(final DataSnapshot ds : dataSnapshot.getChildren()){
                    mStringData1 = ds.child("Fullname").getValue(String.class);
                    mStringData2 = ds.child("Age").getValue(String.class);
                    mStringData3 = ds.child("Height").getValue(String.class);
                    mStringData4 = ds.child("Weight").getValue(String.class);

                    mName.setText(mStringData1);
                    mAge.setText(mStringData2);
                    mHeight.setText(mStringData3);
                    mWeight.setText(mStringData4);

                    if (mStringData3 != null && !"".equals(mStringData3) && mStringData4 != null  &&  !"".equals(mStringData4)) {
                        float heightValue = Float.parseFloat(mStringData3) / 100;
                        float weightValue = Float.parseFloat(mStringData4);

                        float bmi = weightValue / (heightValue * heightValue);

                        displayBMI(bmi);
                    }
                }

                mResultCalc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());
                        View mView = getLayoutInflater().inflate(R.layout.popup_information, null);
                        TextView mResultMnM = (TextView) mView.findViewById(R.id.tv_makanan_minuman_inform);
                        mBuilder.setView(mView);

                        final AlertDialog dialog = mBuilder.create();

                        int usia = Integer.parseInt(mAge.getText().toString());

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
                            resultUsia ="Makanan yang harus dikonsumsi pada usia +"+usia+" tahun adalah gandum utuh, gandum utuh adalah makanan pertama yang wajib dikonsumsi ketika usia sudah mencapai 50 tahun. Fungsinya adalah melancarkan pencernaan karena kandungan seratnya yang tinggi. Selain gandum ada kacang-kacangan, termasuk di dalamnya kacang merah, kacang hitam, dan jenis kacang-kacangan lainnya. Karena kacang-kacangan mengandung serat, protein, dan kalium yang bekerja melawan sodium - biasanya memicu peningkatan tekanan darah. Karena kandungan proteinnya yang tinggi, kacang-kacangan bisa dibilang sebagai pengganti daging bagi vegetarian. \n\nMinuman yang disarankan adalah kopi,  sebab minuman ini juga dianjurkan agar tetap dikonsumsi di usia 50 tahun ke atas. Namun jangan lupa untuk membatasi konsumsinya sebanyak satu cangkir saja setiap hari. Selain kopi juga adalah air mineral.\n";
                        } else if (usia <= 71){
                            resultUsia ="Makanan yang harus dikonsumsi di usia "+usia+" tahun adalah kuncinya pada protein. Rentang usia 60 hingga 75 tahun, membutuhkan nutrisi yang tepat melalui protein. Asupan protein harus lebih banyak, sebab, dapat menurunkan risiko berkurangnya massa otot. Lansia, membutuhkan protein rendah lemak seperti ikan, telur dan tempe. Tidak hanya itu, komponen zat gizi mikro juga dibutuhkan agar menjaga sistem imunnya tetap baik. \n\nMinuman yang disarankan untuk rentang usia ini adalah seperti jus dan smoothies. Adanya jus dan smoothies ini dapat membantu agar lansia tetap terpenuhi kebutuhan nutrisinya. Nah, mulai sekarang saatnya memasukkan ragam jus buah untuk lansia dalam konsumsinya.\n";
                        }
                        mResultMnM.setText(resultUsia);
                        dialog.show();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error show data profile", Toast.LENGTH_LONG).show();
            }
        };
        ref.addListenerForSingleValueEvent(evl);

        return view;
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
