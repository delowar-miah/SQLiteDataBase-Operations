package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.delowar.sqlitecruddemo.MainActivity;
import com.delowar.sqlitecruddemo.R;
import com.delowar.sqlitecruddemo.StudentListActivity;

import java.util.ArrayList;

import database.DataBaseOperation;
import model.Student;

public class CustomAdapter extends ArrayAdapter<Student> {
    private ArrayList<Student> studentArrayList;
    private LayoutInflater layoutInflater;
    private Context context;
    private DataBaseOperation dataBaseOperation;

    public CustomAdapter(@NonNull Context context, ArrayList<Student> studentArrayList) {
        super(context, R.layout.item_layout,studentArrayList);
        this.studentArrayList=studentArrayList;
        this.context=context;
        this.layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class MyViewHolder{
        private TextView nameTextView,cgpaTextView;
        private Button deleteButton,editButton;
        private int rowId;
        private String name,email,phone;
        private Float cgpa;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        dataBaseOperation=new DataBaseOperation(context);
        final MyViewHolder holder;
        if (convertView==null){
            holder=new MyViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_layout,parent,false);
            holder.nameTextView=convertView.findViewById(R.id.textViewUsername);
            holder.cgpaTextView=convertView.findViewById(R.id.textViewCGPA);
            holder.deleteButton=convertView.findViewById(R.id.deletbtn);
            holder.editButton=convertView.findViewById(R.id.editbtn);
            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.rowId=studentArrayList.get(position).getId();
        holder.name=studentArrayList.get(position).getName();
        holder.cgpa=studentArrayList.get(position).getCgpa();
        holder.email=studentArrayList.get(position).getEmail();
        holder.phone=studentArrayList.get(position).getPhone();
        holder.nameTextView.setText(holder.name);
        holder.cgpaTextView.setText(String.valueOf(holder.cgpa));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(context);
                ad.setTitle("Alert dialog");
                ad.setMessage("Are you sure want to delete?");

                ad.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       boolean status=dataBaseOperation.deleteStudentInformation(holder.rowId);
                        if (status){
                            Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, StudentListActivity.class));
                        }
                    }
                });
                ad.setNegativeButton("Cancel",null);
                ad.setCancelable(false);
                AlertDialog dialog=ad.create();
                dialog.show();
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MainActivity.class);
                intent.putExtra("id",holder.rowId);
                intent.putExtra("name",holder.name);
                intent.putExtra("cgpa",holder.cgpa);
                intent.putExtra("email",holder.email);
                intent.putExtra("phone",holder.phone);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
