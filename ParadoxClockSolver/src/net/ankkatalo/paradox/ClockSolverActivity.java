package net.ankkatalo.paradox;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ClockSolverActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(new Slot(2));
        slots.add(new Slot(2));
        slots.add(new Slot(1));
        slots.add(new Slot(1));
        for (int i= 0; i < slots.size(); ++i) {
        	if (ClockSolver.solve(slots, i, 1)) {
        		System.out.println("Found a solution");
        		for (SolutionItem si: ClockSolver.solution) {
        			System.out.println(String.format("index: %d, value: %d", si.index,  si.value));        				
        		}
        		break;
        	}
        }

        
        
        TextView tv = new TextView(this);
        tv.setText("Oh, hi, Android,2!");        
        setContentView(tv);
    }
}