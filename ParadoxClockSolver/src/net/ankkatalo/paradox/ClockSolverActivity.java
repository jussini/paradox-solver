package net.ankkatalo.paradox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.helloandroid.R;

public class ClockSolverActivity extends Activity {
	
	private List<Slot> mSlots = new ArrayList<Slot>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mSlots.clear();        
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
        mSlots.add(new Slot(0));
                
        setContentView(R.layout.main);
                
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        		this, R.array.slot_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        Spinner sp = (Spinner) findViewById(R.id.spinner1);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(1));        
        sp = (Spinner) findViewById(R.id.spinner2);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(2));
        sp = (Spinner) findViewById(R.id.spinner3);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(3));
        sp = (Spinner) findViewById(R.id.spinner4);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(4));
        sp = (Spinner) findViewById(R.id.spinner5);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(5));        
        sp = (Spinner) findViewById(R.id.spinner6);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(6));
        sp = (Spinner) findViewById(R.id.spinner7);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(7));
        sp = (Spinner) findViewById(R.id.spinner8);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(8));        
        sp = (Spinner) findViewById(R.id.spinner9);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(9));        
        sp = (Spinner) findViewById(R.id.spinner10);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(10));
        sp = (Spinner) findViewById(R.id.spinner11);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(11));
        sp = (Spinner) findViewById(R.id.spinner12);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new SlotSpinnerListener(12));        

    }
    
    public void trySolving() {
    	
    	List<Slot> cloneList = new ArrayList<Slot>();
    	cloneList.clear();
    	int largest = 0;
    	for (Slot slot: mSlots) {
    		if (slot.value() > 0) {
    			Slot newSlot = new Slot(slot);
    			newSlot.setAvailable(true);
    			cloneList.add(new Slot(slot));
    			if (newSlot.value() > largest) {
    				largest = newSlot.value();    	    				
    			}
    		}
    	}

    	TextView tv = (TextView) findViewById(R.id.editText1);
    	
    	if (cloneList.size() == 0) {
    		tv.setText("Give some values to the clock face.");
    		return;
    	}
    	    	
    	if (cloneList.size() > 1 && largest > cloneList.size() / 2) {
    		tv.setText(
    				String.format("No solution, largest slot for clock size of %d is %d", 
    				cloneList.size(), 
    				cloneList.size()/2));
    		return;    	    		
    	}
    	
		ClockSolver.solution = new ArrayList<SolutionItem>();
    	boolean foundSolution = false;
        for (int i= 0; i < cloneList.size(); ++i) {
        	if (ClockSolver.solve(cloneList, i, 1)) {
        		foundSolution = true;
        		break;
        	}
        }
        if (foundSolution) {
    		System.out.println("Found a solution");
    		StringBuilder builder = new StringBuilder();
    		for (SolutionItem si: ClockSolver.solution) {
    			builder.append(String.format("index: %d, value: %d\n", si.index,  si.value));
    			//System.out.println(String.format("index: %d, value: %d", si.index,  si.value));        				
    		}    		
    		tv.setText("");
    		tv.setText(builder.toString());
        } else {
        	tv.setText("Given clock face is impossible to solve.");
        }
    	
    }
    
    
    private class SlotSpinnerListener implements OnItemSelectedListener {
    	
    	private int m_slot = -1;
    	public SlotSpinnerListener (int slot) {
    		this.m_slot = slot;    		
    	}
    	
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			System.out.println("onItemSelected");
			System.out.println(m_slot);
    		String value = parent.getItemAtPosition(pos).toString();
    		mSlots.get(m_slot-1).setValue(Integer.parseInt(value));

			trySolving();			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
        	
    }
    
}