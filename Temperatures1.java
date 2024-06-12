package lab1;

import java.util.*; // Scanner, Locale
import static java.lang.System.out;
    class Temperatures1
    {
        public static void main (String[] args)
        {
        out.println("TEMPERATURES\n");
        // input tool
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);
        // enter the number of weeks and measurements
        out.print("number of weeks: ");
        int nofWeeks = in.nextInt();
        out.print("number of measurements per week: ");
        int nofMeasurementsPerWeek = in.nextInt();
        // storage space for temperature data
        double[][] t = new double[nofWeeks + 1]
        [nofMeasurementsPerWeek + 1];
        // read the temperatures
        for (int week = 1; week <= nofWeeks; week++)
            {
            out.println("temperatures - week " + week + ":");
            for (int measurement = 1;
            measurement <= nofMeasurementsPerWeek;
            measurement++)
            t[week][measurement] = in.nextDouble();
            }
        out.println("");
        // show the temperatures
        out.println("the temperatures");
        for (int week = 1; week <= nofWeeks; week++)
        {
            for (int measurement = 1;
            measurement <= nofMeasurementsPerWeek;
            measurement++)
            out.print(t[week][measurement] + " ");
            out.println("");
        }
    out.println("");
    // the least, greatest and average temperatures - weekly
    double[] minT = new double[nofWeeks + 1];
    double[] maxT = new double[nofWeeks + 1];
    double[] sumT = new double[nofWeeks + 1];
    double[] avgT = new double[nofWeeks + 1];
    // add code here

    // Compute the minimum temperature for each week
    for (int week = 1; week <= nofWeeks; week++) {
    // Initialize minTemp for the current week
    double weekMinTemp = t[week][1];
    // Iterate through measurements to find the minimum temperature
    for (int measurement = 2; measurement <= nofMeasurementsPerWeek; measurement++) {
        double temp = t[week][measurement];
        if (temp < weekMinTemp) {
            weekMinTemp = temp;
        }
    }
    // Store the minimum temperature for the current week
    minT[week] = weekMinTemp;
    }


    // Compute the maximum temperature for each week
    for (int week = 1; week <= nofWeeks; week++) {
    // Initialize maxTemp for the current week
    double weekMaxTemp = t[week][1];
    // Iterate through measurements to find the maximum temperature
    for (int measurement = 2; measurement <= nofMeasurementsPerWeek; measurement++) {
        double temp = t[week][measurement];
        if (temp > weekMaxTemp) {
            weekMaxTemp = temp;
        }
    }
    // Store the maximum temperature for the current week
    maxT[week] = weekMaxTemp;
    }

    for (int week = 1; week <= nofWeeks; week++) {
        // Initialize sum for the current week
        double sum = t[week][1];
        // Iterate through measurements to calculate the sum of temperatures
        for (int measurement = 2; measurement <= nofMeasurementsPerWeek; measurement++) {
            sum += t[week][measurement];
        }   
        // Calculate the average temperature for the current week
        avgT[week] = sum / nofMeasurementsPerWeek;
    }




    // show the least, greatest and average temperatures
    out.println("the least, greatest and average temperatures"
    + " - weekly");
    for (int week = 1; week <= nofWeeks; week++)
    out.print(minT[week] + " ");
    out.println("");
    for (int week = 1; week <= nofWeeks; week++)
    out.print(maxT[week] + " ");
    out.println("");
    for (int week = 1; week <= nofWeeks; week++)
    out.print(avgT[week] + " ");
    out.println("");
    out.println();
    // the least, greatest and average temperatures - whole period
    double minTemp = minT[1];
    double maxTemp = maxT[1];
    double sumTemp = sumT[1];
    double avgTemp = 0;
    // add code here

    // Determine the minimum, maximum, and average temperatures for the whole period
    for (int week = 1; week <= nofWeeks; week++) {
        // Update minTemp for the whole period
        if (minT[week] < minTemp) {
            minTemp = minT[week];
        }
        // Update maxTemp for the whole period
        if (maxT[week] > maxTemp) {
            maxTemp = maxT[week];
        }
        // Accumulate sumTemp for the whole period
        for (int measurement = 1; measurement <= nofMeasurementsPerWeek; measurement++) {
            sumTemp += t[week][measurement];
        }
    }
    // Calculate the average temperature for the whole period
    avgTemp = sumTemp / (nofWeeks * nofMeasurementsPerWeek);

    // show the least, greatest and average temperature for
    // the whole period
    out.println("the least, greatest and average temperature"
    + " - whole period");
    out.println(minTemp + "\n" + maxTemp + "\n" + avgTemp);
    }
}