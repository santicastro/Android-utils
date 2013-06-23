package es.skastro.android.util.alert;

import java.util.List;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * SimpleClientAdapter is used for the spinner
 */
public class SimpleListAdapter<T> extends ArrayAdapter<T> {
    List<T> results;
    Activity context;
    final float scale;
    StringGenerator<T> stringGenerator;

    public SimpleListAdapter(Activity context, List<T> results, StringGenerator<T> stringGenerator) {
        super(context, android.R.layout.simple_list_item_1, results);
        this.context = context;
        this.results = results;
        this.stringGenerator = stringGenerator;
        scale = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        final T element = results.get(position);
        ((TextView) item).setText(Html.fromHtml(stringGenerator.getString(element)));
        return item;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    public T getItem(int position) {
        return results.get(position);
    }

    public interface StringGenerator<T> {
        String getString(T element);
    }
}
