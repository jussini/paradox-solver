package net.ankkatalo.paradox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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
/*
        StringBuilder builder = new StringBuilder();
        for (int i= 0; i < slots.size(); ++i) {
        	if (ClockSolver.solve(slots, i, 1)) {
        		System.out.println("Found a solution");
        		for (SolutionItem si: ClockSolver.solution) {
        			builder.append(String.format("index: %d, value: %d\n", si.index,  si.value));
        			//System.out.println(String.format("index: %d, value: %d", si.index,  si.value));        				
        		}
        		break;
        	}
        }
        */
                
        setContentView(R.layout.main);
        
        EditText te = (EditText) findViewById(R.id.slot1);
        te.addTextChangedListener(new SlotWatcher(1));
        te = (EditText) findViewById(R.id.slot2);
        te.addTextChangedListener(new SlotWatcher(2));
        te = (EditText) findViewById(R.id.slot3);
        te.addTextChangedListener(new SlotWatcher(3));
        te = (EditText) findViewById(R.id.slot4);
        te.addTextChangedListener(new SlotWatcher(4));
        te = (EditText) findViewById(R.id.slot4);
        te.addTextChangedListener(new SlotWatcher(5));
        te = (EditText) findViewById(R.id.slot5);
        te.addTextChangedListener(new SlotWatcher(6));
        te = (EditText) findViewById(R.id.slot6);
        te.addTextChangedListener(new SlotWatcher(7));
        te = (EditText) findViewById(R.id.slot7);
        te.addTextChangedListener(new SlotWatcher(8));
        te = (EditText) findViewById(R.id.slot8);
        te.addTextChangedListener(new SlotWatcher(9));
        te = (EditText) findViewById(R.id.slot9);
        te.addTextChangedListener(new SlotWatcher(10));
        te = (EditText) findViewById(R.id.slot10);
        te.addTextChangedListener(new SlotWatcher(11));
        te = (EditText) findViewById(R.id.slot11);
        te.addTextChangedListener(new SlotWatcher(12));
        te = (EditText) findViewById(R.id.slot12);
        
        
        //TextView tv = (TextView) findViewById(R.id.textview);
        //tv.setText(builder.toString());        
    }
    

    private class SlotWatcher implements TextWatcher {
    	
    	private int mSlot = -1;
    	
    	public SlotWatcher(int slot) {
    		mSlot = slot;    		
    	}
    	
    	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
    			int arg3) {
    		// TODO Auto-generated method stub    		
    	}

    	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    		// TODO Auto-generated method stub    		
    	}

    	public void afterTextChanged(Editable s) {
    	    	System.out.println(String.format("text of slot %d was changed to %s!", mSlot, s.toString()));
    	    	
    	    	if (s.toString().equalsIgnoreCase("")) {
        	    	mSlots.get(mSlot-1).setValue(0);    	    		
    	    	} else {
        	    	mSlots.get(mSlot-1).setValue(Integer.parseInt(s.toString()));    	    		    	    		
    	    	}
    	    	
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
    	    	if (largest > cloneList.size() / 2.0) {
    	    		System.out.println("No solution");
    	    		return;    	    		
    	    	}
    	    	    	      	      	    	
    	    	System.out.println(cloneList.size());
    	    	
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
	        		TextView tv = (TextView) findViewById(R.id.editText1);
	        		tv.setText("");
	        		tv.setText(builder.toString());
    	        } else {
    	        	System.out.println("No solution");    	        	
    	        }

    	}
    	
    	
    }
    
}