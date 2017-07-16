package fingertip.creditease.com.filestorage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private TextView textViewFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.test_et);
        textView= (TextView) findViewById(R.id.test_read);
        textViewFile= (TextView) findViewById(R.id.test_file);
    }

    public void save()
    {
        try {

            FileOutputStream outStream=this.openFileOutput("text.txt", Context.MODE_APPEND);
            outStream.write(editText.getText().toString().getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            return;
        }
        catch (IOException e){
            return ;
        }
    }



    public void saveTest(View view){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        save();
    }

    public void readTest(View view){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        read();
    }


    public void read()
    {
        try {
            FileInputStream inStream=this.openFileInput("text.txt");
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int length=-1;
            while((length=inStream.read(buffer))!=-1)   {
                stream.write(buffer,0,length);
            }
            stream.close();
            inStream.close();
            textView.setText(stream.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            return ;
        }
    }

    public void getFiles(View view){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        String[] file=fileList();
        StringBuffer stringBuffer=new StringBuffer();
        for(String name:file){
            stringBuffer.append(name+"\n");
        }
        textViewFile.setText(stringBuffer);

    }
}
