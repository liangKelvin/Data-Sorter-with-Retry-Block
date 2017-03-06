import java.util.ArrayList;
import java.lang.Math;

public class HeapSort {

    private ArrayList<Integer> localV = new ArrayList<Integer>();
    private static int n;
    private int mem_acc_count = 0;

    public ArrayList<Integer> sort(ArrayList<Integer> values) {

        this.localV = values;
        this.mem_acc_count++;
        BuildHeap();
        for(int i = n; i > 0; i--) {
            swap(0, i);
            n--;
            Heapify(0);
        }

        return this.localV;
    }

    private void BuildHeap() {
        n = this.localV.size()-1;
        this.mem_acc_count++;
        for(int i = (int)Math.floor(n/2); i >= 0; i--) {
            Heapify(i);
        }
    }

    private void Heapify(int i) {
        int left = 2*i;
        int right = 2*i+1;
        int max = i;

        if(left <= n && this.localV.get(left) > this.localV.get(i)) {
            max = left;
        } 
        this.mem_acc_count += 2;
        if(right <= n && this.localV.get(right) > this.localV.get(max)) {
            max = right;
        }
        this.mem_acc_count += 2;
        if(max != i) {
            swap(i, max);
            Heapify(max);
        }
    }

    private void swap(int x, int y) {
        int temp = this.localV.get(x);
        this.localV.set(x, this.localV.get(y));
        this.localV.set(y, temp);
        this.mem_acc_count += 4;
    }

    public int getMemCount() {
        return this.mem_acc_count;
    }
}