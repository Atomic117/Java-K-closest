/* ---------------------------------------------------------------------------------
The PointSet class that contains an ArrayList of LabelledPoint instances.
It also reads fvecs files from corpus-texmex.irisa.fr



THOMAS TRAN
Edited main method to now use kNN
------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.ArrayList;

class PointSet {
    private ArrayList<LabelledPoint> pointsList = new ArrayList<>();
	
	// constructs an empty point set
    public PointSet(){  
        
    }

    // constructs a point set for the given ArrayList instance
    public PointSet(ArrayList<LabelledPoint> pointsList){
       this.pointsList = pointsList;
    }

    // read from fvecs file
    public static ArrayList<LabelledPoint> read_ANN_SIFT(String filename) {
        ArrayList<LabelledPoint> pointSet = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);

            int d = Integer.reverseBytes(dis.readInt());
            int vecSizeOf = 1 * 4 + d * 4;
            long fileLength = new File(filename).length();
            int bMax = (int) (fileLength / vecSizeOf);
            int a = 1;
            int b = bMax;

            if( a >= 1 && b>bMax){
                b =bMax;
            }

            if (b == 0 || b < a) {
                dis.close();
				throw new IOException("Error! Invalid file format...");
            }

            int n = b - a + 1;
            
            dis.skipBytes((a - 1) * vecSizeOf);
            for (int i = 0; i < n; i++) {
                Float[] vector = new Float[d];
                for (int j = 0; j < d; j++) {
                    int floatAsInt = Integer.reverseBytes(dis.readInt());
                    vector[j] = Float.intBitsToFloat(floatAsInt);
                }
                dis.skipBytes(4);
                pointSet.add(new LabelledPoint(vector,i));
            }

            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pointSet;
    }

    // get the ArrayList instance containing the point set
    public ArrayList<LabelledPoint> getPointsList() {
        return pointsList;
    }

    // set the point set
    public void setPointsList (ArrayList<LabelledPoint> pointsList){
        this.pointsList = pointsList;
    }

    public static void main(String[] args) {

        // Getting main arguements
        int versionNumber = Integer.parseInt(args[0]);
        int kNumber = Integer.parseInt(args[1]);
        String dataFile = args[2];
        String queryFile = args[3];


		// read query points
        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT(queryFile));
		// read point set
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT(dataFile));

        /*
        System.out.println("Query set: "+queryPts.getPointsList().size());
        System.out.println("Point set: "+pointSet.getPointsList().size());
	    */
		long startTime = System.currentTimeMillis(); // start timer

        ArrayList<LabelledPoint> list;
        for (int i = 0; i < queryPts.getPointsList().size(); i++){
            //Initializing and setting KNN
            KNN closestKI = new KNN(pointSet);
            closestKI.setK(kNumber);
            closestKI.setQuery(queryPts.getPointsList().get(i));

            //Finding kNN
            list = closestKI.findNN(versionNumber);

            //Prints out kNN
            System.out.print(i +": ");
            for (int j = 0; j < kNumber; j ++){
                if (j != kNumber-1) {
                    System.out.print(list.get(j).getLabel() + ", ");
                } else {
                    System.out.print(list.get(j).getLabel());
                }
            }
            System.out.println(" ");

        }
		
		long endTime = System.currentTimeMillis(); // end timer
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
	}
}

