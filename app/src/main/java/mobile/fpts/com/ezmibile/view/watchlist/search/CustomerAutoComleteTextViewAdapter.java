//package mobile.fpts.com.ezmibile.view.watchlist.search;
//
//import android.content.SharedPreferences;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Filter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import mobile.fpts.com.ezmibile.App;
//import mobile.fpts.com.ezmibile.R;
//import mobile.fpts.com.ezmibile.util.Define;
//
//public class CustomerAutoComleteTextViewAdapter extends ArrayAdapter<CustomerAutoComleteTextView> {
//    public ArrayList<CustomerAutoComleteTextView> customerAutoComleteTextViews, tempCustomerAutoComleteTextView, suggestions ;
//
//    public CustomerAutoComleteTextViewAdapter(ArrayList<CustomerAutoComleteTextView> objects) {
//        super(App.getInstance(), android.R.layout.simple_list_item_1, objects);
//        this.customerAutoComleteTextViews = objects ;
//        this.tempCustomerAutoComleteTextView =new ArrayList<>(objects);
//        this.suggestions =new ArrayList<>(objects);
//
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        CustomerAutoComleteTextView customerAutoComleteTextView = getItem(position);
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_watchlist_search_row, parent, false);
//        }
//
//        TextView text1 = convertView.findViewById(R.id.text1);
//        TextView text2 = convertView.findViewById(R.id.text2);
//        TextView text3 = convertView.findViewById(R.id.text3);
//
//        if (text1 != null)
//            text1.setText(customerAutoComleteTextView.getStock_code());
////
//        if (text3 != null) {
//            text3.setText(" - " + customerAutoComleteTextView.getPost_to());
//        }
//
//        if (text2 != null) {
//            if (getLanguageFormSPR().equals("en")) {
//                text2.setText(" - " + customerAutoComleteTextView.getName_en());
//            } else {
//                text2.setText(" - " + customerAutoComleteTextView.getName_vn());
//            }
//        }
//
//        return convertView;
//    }
//
//    public String getLanguageFormSPR () {
//        SharedPreferences pre = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, App.getInstance().MODE_PRIVATE);
//        String maCK = pre.getString(Define.SHARED_PREFRENCES_LANGUAGE, "vi");
//
//        return maCK;
//    }
//
//    @Override
//    public Filter getFilter() {
//        return myFilter;
//    }
//
//    Filter myFilter = new Filter() {
//        @Override
//        public CharSequence convertResultToString(Object resultValue) {
//            CustomerAutoComleteTextView customerAutoComleteTextView = (CustomerAutoComleteTextView) resultValue;
//            return customerAutoComleteTextView.getStock_code();
//        }
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            if (constraint != null) {
//                suggestions.clear();
//                for (CustomerAutoComleteTextView people : tempCustomerAutoComleteTextView) {
//                    if (people.getStock_code().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
//                        suggestions.add(people);
//                    }
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = suggestions;
//                filterResults.count = suggestions.size();
//                return filterResults;
//            } else {
//                return new FilterResults();
//            }
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            ArrayList<CustomerAutoComleteTextView> c = (ArrayList<CustomerAutoComleteTextView>) results.values;
//            if (results != null && results.count > 0) {
//                clear();
//                for (CustomerAutoComleteTextView cust : c) {
//                    add(cust);
//                    notifyDataSetChanged();
//                }
//            }
//            else{
//                clear();
//                notifyDataSetChanged();
//            }
//        }
//    };
//}
