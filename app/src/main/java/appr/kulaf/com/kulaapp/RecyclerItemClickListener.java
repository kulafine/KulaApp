package appr.kulaf.com.kulaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Prince on 1/24/2017.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnitemClick(View view, int position);
    }

    GestureDetector gestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener mListener) {
        this.mListener = mListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View v = rv.findChildViewUnder(e.getX(), e.getY());

        if (v != null && mListener != null && gestureDetector.onTouchEvent(e)){
            mListener.OnitemClick(v, rv.getChildAdapterPosition(v));

        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
