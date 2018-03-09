package app.engine.android.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import app.engine.android.R;
import app.engine.android.model.Category;


class CategoryViewHolder {
    public Button button;
}

class CategoryMenuAdapter extends BaseAdapter {
    private final Context context;
    private final List<Category> values;

    public CategoryMenuAdapter(Context context, List<Category> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.profile_menu_listview, parent, false);
            CategoryViewHolder viewHolder = new CategoryViewHolder();
            viewHolder.button = rowView.findViewById(R.id.menuBtn);
            rowView.setTag(viewHolder);
        }

        CategoryViewHolder holder = (CategoryViewHolder) rowView.getTag();
        holder.button.setText(values.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditProfilePagesActivity.class);
                intent.putExtra("CAT_NAME", values.get(position).getName());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}

