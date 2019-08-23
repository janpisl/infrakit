import static java.lang.Math.abs;
import java.util.List; 
import java.util.Scanner;
import java.util.ArrayList; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Triangle volume
1. Create a method in Java which calculates the volume between these two triangles:
[25492673.593,6678072.773,4.615], [25492719.473,6678050.171,6.968], [25492697.753,6678048.506,6.492]


[25492673.593,6678072.773,5.415], [25492719.473,6678050.171,8.168],
[25492697.753,6678048.506,7.392]	
*/


public class Volume {

    // Read input data from a text file, store them in
    // a list of Triangle objects and print the result
    public static void main(String[] args) throws Exception {
        
        Path pathFile= Paths.get(args[0]);
        String content = Files.readString(pathFile).replaceAll("[^0-9.]", " ");
        List<Triangle> triangles = new ArrayList<>();

        Scanner sc=new Scanner(content);
        for (int i = 0; i < 2; i++) {
            triangles.add(new Triangle(new Point(sc.nextDouble(),sc.nextDouble(),sc.nextDouble()),
                                            new Point(sc.nextDouble(),sc.nextDouble(),sc.nextDouble()),
                                                new Point(sc.nextDouble(),sc.nextDouble(),sc.nextDouble())));
        }

        System.out.println("The volume between the two given triangles is: " + volume(triangles));

    }


    // Calculate the volume between two given triangles
    private static double volume(List<Triangle> triangles) {

        return abs((integral(triangles.get(0))) - (integral(triangles.get(1))));
    }

    // Calculate the volume under a given triangle
    private static double integral(Triangle tr) {

        // Formula: V = (z1+z2+z3)(x1y2-x2y1+x2y3-x3y2+x3y1-x1y3)/6
        return (tr.getP1().getZ() + tr.getP2().getZ() + tr.getP3().getZ()) * (tr.getP1().getX() * 
                    tr.getP2().getY() - tr.getP2().getX() * tr.getP1().getY() + tr.getP2().getX() * 
                        tr.getP3().getY() - tr.getP3().getX() * tr.getP2().getY() + tr.getP3().getX() * 
                            tr.getP1().getY() - tr.getP1().getX() * tr.getP3().getY())/6;

    }

}
