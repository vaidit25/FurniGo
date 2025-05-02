package com.example.myproject.adapter;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.ProductDetail;
import com.example.myproject.R;
import com.example.myproject.model.ProductModel;
import com.example.myproject.utils.ConstantsData;

import java.util.List;


import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<ProductModel> {

    private List<ProductModel> originalData; // Original data list
    private List<ProductModel> filteredData; // Filtered data list
    private ItemFilter filter;

    public SearchAdapter(Context context, List<ProductModel> data) {
        super(context, 0, data);
        this.originalData = data;
        this.filteredData = new ArrayList<>(data);
        filter = new ItemFilter();
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public ProductModel getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate a custom layout for each item in the AutoCompleteTextView
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_search, parent, false);
        }

        ProductModel product = getItem(position);

        // Set product name
        TextView productName = convertView.findViewById(R.id.txtProName);
        productName.setText(product.getProductname());

        // Set product image using Glide (optional)
        ImageView productImage = convertView.findViewById(R.id.img);
        Glide.with(getContext())
                .load(ConstantsData.SERVER_ADDRESS_IMG+product.getPic1()) // Use the product image URL
                .into(productImage);

//

        CardView searchcard= convertView.findViewById(R.id.searchcard);

        searchcard.setOnClickListener(v -> {
            Intent intent=new Intent(getContext(), ProductDetail.class);
            intent.putExtra("model",product);
            getContext().startActivity(intent);
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            // If there is no query, return the original data
            if (constraint == null || constraint.length() == 0) {
                results.values = originalData;
                results.count = originalData.size();
            } else {
                // Filter based on the product name
                List<ProductModel> filteredList = new ArrayList<>();
                for (ProductModel product : originalData) {
                    if (product.getProductname().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(product);
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                filteredData = (List<ProductModel>) results.values;
                notifyDataSetChanged(); // Notify adapter about data changes
            }
        }
    }
}