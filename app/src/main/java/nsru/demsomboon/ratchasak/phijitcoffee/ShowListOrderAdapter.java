package nsru.demsomboon.ratchasak.phijitcoffee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 10/19/2016 AD.
 */

public class ShowListOrderAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] dateStrings, coffeeStrings,
            amountStrings, statusStrings;
    private TextView dateTextView, coffeeTextView,
            amountTextView, statusTextView;

    public ShowListOrderAdapter(Context context,
                                String[] dateStrings,
                                String[] coffeeStrings,
                                String[] amountStrings,
                                String[] statusStrings) {
        this.context = context;
        this.dateStrings = dateStrings;
        this.coffeeStrings = coffeeStrings;
        this.amountStrings = amountStrings;
        this.statusStrings = statusStrings;
    }

    @Override
    public int getCount() {
        return coffeeStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.show_list_order_listview, viewGroup, false);

        //Bind Widget
        dateTextView = (TextView) view1.findViewById(R.id.textView39);
        coffeeTextView = (TextView) view1.findViewById(R.id.textView40);
        amountTextView = (TextView) view1.findViewById(R.id.textView41);
        statusTextView = (TextView) view1.findViewById(R.id.textView42);

        //Show View
        dateTextView.setText(dateStrings[i]);
        coffeeTextView.setText(coffeeStrings[i]);
        amountTextView.setText(amountStrings[i]);
        statusTextView.setText(statusStrings[i]);

        return view1;
    }
}   // Main Class
