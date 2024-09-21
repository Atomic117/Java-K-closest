/*-----------------------------------------------------------------------
THOMAS TRAN  CSI 2110
KNN class is responsible for finding the K nearest neighbours (based
on distance), returning a list containing neighbours.

Depending on the version, it will call the method findNN1, findNN2, findNN3, respectively
using a priority queue implemented by array, heap, import

It contains methods used to set the value of K and queryPoint before performing operations
 ---------------------------------------------------------------------*/

/* IMPORTS */
import java.util.ArrayList;

public class KNN{

    /* CLASS VARIABLES */
    PointSet data;
    LabelledPoint queryPoint;
    int k;

    //CONSTRUCTOR

    public KNN(PointSet x){
        data = x;
    }

    /* SET METHODS */
    public void setK(int x){
        k = x;
    }

    public void setQuery(LabelledPoint point){
        queryPoint = point;
    }

    //Method takes in a version before performing execution
    public ArrayList<LabelledPoint> findNN(int version){
        switch (version){
            case 1:
                return findNN1();
            case 2:
                return findNN2();
            case 3:
                return findNN3();
            default:
                return null;
        }
    }

    /* FIND NN METHODS */

    //Method to find NN using an array
    private ArrayList<LabelledPoint> findNN1(){
        //Creates the list to contain the NN and the datapoints
        ArrayList<LabelledPoint> closestNN = new ArrayList<>();
        ArrayList<LabelledPoint> dataList = data.getPointsList();
        //Creates the array priority queue
        PriorityQueue1 queue = new PriorityQueue1(k);
        //Iterates through the dataList and calculates distance, setting it as the key
        for (int i = 0; i < dataList.size(); i++){
            LabelledPoint x = dataList.get(i);
            x.setKey(queryPoint.distanceTo(x));
            //Checks if the queue is full, if not, inserts it (the queue will sort itself in the class)
            if (queue.offer(x)){}
            //If full, checks if the new point contains smaller distance to Query than the MAX point (which will have
            // most distance in that queue)
            else if (x.compareTo((LabelledPoint)queue.peek()) < 0){
                //If the new point is better, removes the max point and inserts the new point
                queue.poll();
                queue.offer(x);
            }
        }

        //Retrieves the points from the priority queue and inserts into the array list
        for (int i = 0; i < k; i++){
            closestNN.add((LabelledPoint)queue.poll());
        }
        return closestNN;
    }

    //Method to find NN using a heap
    private ArrayList<LabelledPoint> findNN2(){
        //Creates the list to contain the NN and the datapoints
        ArrayList<LabelledPoint> closestNN = new ArrayList<>();
        ArrayList<LabelledPoint> dataList = data.getPointsList();
        //Creates the heap priority queue
        PriorityQueue2 queue = new PriorityQueue2(k);
        //Iterates through the dataList and calculates distance, setting it as the key
        for (int i = 0; i < dataList.size(); i++){
            LabelledPoint x = dataList.get(i);
            x.setKey(queryPoint.distanceTo(x));
            //Checks if the queue is full, if not, inserts it (the queue will sort itself in the class)
            if (queue.offer(x)){}
            //If full, checks if the new point contains smaller distance to Query than the MAX point (which will have
            // most distance in that queue)
            else if (x.compareTo((LabelledPoint)queue.peek()) < 0){
                //If the new point is better, removes the max point and inserts the new point
                queue.poll();
                queue.offer(x);
            }
        }

        //Retrieves the points from the priority queue and inserts into the array list
        for (int i = 0; i < k; i++){
            closestNN.add((LabelledPoint)queue.poll());
        }
        return closestNN;
    }

    //Find NN using the imported Priority Queue
    private ArrayList<LabelledPoint> findNN3(){
        //Creates the list to contain the NN and the datapoints
        ArrayList<LabelledPoint> closestNN = new ArrayList<>();
        ArrayList<LabelledPoint> dataList = data.getPointsList();
        //Creates the imported priority queue
        PriorityQueue3 queue = new PriorityQueue3(k);
        //Iterates through the dataList and calculates distance, setting it as the key
        for (int i = 0; i < dataList.size(); i++){
            LabelledPoint x = dataList.get(i);
            x.setKey(queryPoint.distanceTo(x));
            //Checks if the queue is full, if not, inserts it (the queue will sort itself in the class)
            if (queue.offer(x)){}
            //If full, checks if the new point contains smaller distance to Query than the MAX point (which will have
            // most distance in that queue)
            else if (x.compareTo((LabelledPoint)queue.peek()) < 0){
                //If the new point is better, removes the max point and inserts the new point
                queue.poll();
                queue.offer(x);
            }
        }

        //Retrieves the points from the priority queue and inserts into the array list
        for (int i = 0; i < k; i++){
            closestNN.add((LabelledPoint)queue.poll());
        }
        return closestNN;
    }
}
