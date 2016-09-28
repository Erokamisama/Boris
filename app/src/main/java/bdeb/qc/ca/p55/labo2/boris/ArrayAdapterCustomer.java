package bdeb.qc.ca.p55.labo2.boris;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ca.qc.bdeb.p55.labo2.BD.DBHelper;
import ca.qc.bdeb.p55.labo2.Client.Client;

/**
 * Created by WuTchanKi on 2016-09-14.
 */
public class ArrayAdapterCustomer extends ArrayAdapter<Client> {
    Context context;
    List<Client> clients;
    DBHelper dbHelper;


    public ArrayAdapterCustomer(Context context, int resource, List<Client> objects) {
        super(context, resource, objects);
        this.context = context;
        this.clients = objects;
        dbHelper = dbHelper.getInstance(context);
    }

    private class CustomerHolder {
        ImageView imgCustomer;
        TextView name;
        TextView lastName;
        TextView age;
        TextView city;
        ImageButton btnEdit;
        ImageButton btnDelete;
        ImageButton btnView;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CustomerHolder holder = null;
        Client rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_display_listview, null);
            holder = new CustomerHolder();


            holder.name = (TextView) convertView.findViewById(R.id.custom_layout_FirstName);
            holder.lastName = (TextView) convertView.findViewById(R.id.custom_layout_LastName);
            holder.city = (TextView) convertView.findViewById(R.id.custom_layout_City);
            holder.age = (TextView) convertView.findViewById(R.id.custom_layout_Age);
            holder.btnEdit = (ImageButton) convertView.findViewById(R.id.custom_layout_btnEdit);
            holder.btnDelete = (ImageButton) convertView.findViewById(R.id.custom_layout_btnDelete);
            holder.imgCustomer = (ImageView) convertView.findViewById(R.id.custom_layout_imgContact);
            holder.btnView = (ImageButton) convertView.findViewById(R.id.custom_layout_btnView);

            convertView.setTag(holder);


        } else {
            holder = (CustomerHolder) convertView.getTag();
        }
        holder.name.setText(clients.get(position).getFirstName());
        holder.lastName.setText(clients.get(position).getLastName());
        holder.city.setText(clients.get(position).getCity());
        holder.age.setText(clients.get(position).getAge() + "");
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editClient(clients.get(position).getIdClient());
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteClient(clients.get(position));

                clients.remove(position);


                notifyDataSetChanged();

            }
        });

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("2500000000");

            }
        });
        if (clients.get(position).getGender().equals("M")) {
            holder.imgCustomer.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.contacthomme));
        } else {
            holder.imgCustomer.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.contactfemme));
        }


        return convertView;

    }

    private void editClient(long idClient) {



    }
}
