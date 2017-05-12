package bottomnav.hitherejoe.com.pulse.fragment;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import bottomnav.hitherejoe.com.pulse.R;
import bottomnav.hitherejoe.com.pulse.adapter.CustomAdapter;
import bottomnav.hitherejoe.com.pulse.model.DBHelper;
import bottomnav.hitherejoe.com.pulse.model.Notification;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class AlarmFragment extends Fragment {
    public int count = 0;
    public int ID = -1;
    DBHelper mHelper;
    private static final String TAG = "MainFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        mHelper = new DBHelper(getActivity());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Signalref = database.getReference("/Pulse");
        final ArrayList<Integer> knowledge = mHelper.getKnowledge();

        Signalref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final long g = (long) dataSnapshot.child("Signal").getValue();
                final String t = (String) dataSnapshot.child("Time").getValue();
                if (g < knowledge.get(1) && count == 0) {
                    final MediaPlayer alert = MediaPlayer.create(getContext(),R.raw.alert);
                    alert.start();
                    count = 1;
                    Log.d(TAG, "CountUpdate : " + count);
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("ชีพจรปัจจุบัน : " + g)
                            .setContentText("ชีพจรต่ำอาจเกิดอันตราย")
                            .setConfirmText("รับทราบ")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    count = 0;
                                    Notification noti = new Notification();
                                    noti.setBpm(g);
                                    noti.setDate(t);
                                    noti.setRecommend("อันตรายยย");

                                    if (ID == -1) {
                                        mHelper.addNotification(noti);
                                    } else {
                                        noti.setId(ID);
                                        //mHelper.updateFriend(friend);
                                    }
                                    sDialog.cancel();
                                    alert.stop();
                                }
                            })
                            .show();
                }
                Log.d(TAG, "Signal : " + g);
                Log.d(TAG, "Count : " + count);
            }
            //
            @Override
            public void onCancelled(DatabaseError databaseError) {
//                textError.setText("sdasdas" + databaseError.getMessage());
                Log.d(TAG,"Read Error : " + databaseError.getMessage());
            }
        });
        List<String> notification = mHelper.getNotificationList();
        CustomAdapter adapter = new CustomAdapter(getActivity(), notification);

        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

}
