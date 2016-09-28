package bdeb.qc.ca.p55.labo2.boris;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.qc.bdeb.p55.labo2.BD.DBHelper;
import ca.qc.bdeb.p55.labo2.Client.Client;

public class ManageClient extends AppCompatActivity {
    private ListView listViewCustomers;
    private ArrayList<Client> customersList = new ArrayList<Client>();
    ArrayAdapterCustomer adapter;
    static final int RECUPERER_CLIENT = 1;
    DBHelper dbHelper;
    ImageButton btnEdit;
    ImageButton btnDelete;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_customers);


        dbHelper = DBHelper.getInstance(this);
        customersList = dbHelper.recupererListeClient();




        listViewCustomers = (ListView) findViewById(R.id.activity_manage_customer_lstView);

        adapter = new ArrayAdapterCustomer(this, R.layout.custom_display_listview, customersList);
       listViewCustomers.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage_customer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addContact:
                addCustomer();

                return true;


            case R.id.aboutUs:
                   showMsgAboutUs();
                return true;


            default:
                return true;
        }
    }

    private void showMsgAboutUs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.aboutUs);
        builder.setMessage(R.string.aboutUsMsg);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();
    }


    private void addCustomer() {
        Intent openAddContactActivity = new Intent(this, AddNewClient.class );
        startActivityForResult(openAddContactActivity, RECUPERER_CLIENT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            if (requestCode == RECUPERER_CLIENT)
                if(resultCode == AppCompatActivity.RESULT_OK){
                    Client client =(Client) data.getSerializableExtra(AddNewClient.RETOUR_CLIENT);
                    customersList.add(client);
                    dbHelper = DBHelper.getInstance(getApplicationContext());
                    dbHelper.ajouterClient(client);
                    listViewCustomers.deferNotifyDataSetChanged();


                }

        }



    }

