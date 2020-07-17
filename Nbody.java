public class NBody
{

    public static double readRadius(String filename)
    {
        In file = new In(filename);
        int number_of_planets = file.readInt();
        double radius_of_universe = file.readDouble();
        return radius_of_universe;
    }


    public static Planet[] readPlanets(String filename)
    {
        In file = new In(filename);
        int number_of_planets = file.readInt();
        double radius_of_universe = file.readDouble();
        Planet[] x = new Planet[number_of_planets];
        for (int i = 0; i < number_of_planets; i++)
        {
            double xX = file.readDouble();
            double yX = file.readDouble();
            double xV = file.readDouble();
            double yV = file.readDouble();
            double m = file.readDouble();
            String img = file.readString();
            x[i] = new Planet(xX, yX, xV, yV, m, img);
        }
        return x;
    }


    public static void main(String[] args)
    {
        double t = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] array = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();
        for (int i = 0; i < array.length; i++)
        {
            array[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        for (double time = 0; time < t; time = time + dt)
        {
            double[] xForces = new double[array.length];
            double[] yForces = new double[array.length];
            for (int i = 0; i < array.length; i++)
            {
                xForces[i] = array[i].calcNetForceExertedByX(array);
                yForces[i] = array[i].calcNetForceExertedByY(array);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < array.length; i++)
            {
                array[i].update(dt, xForces[i], yForces[i]);
                array[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", array.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < array.length; i++)
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    array[i].xxPos, array[i].yyPos, array[i].xxVel,
                    array[i].yyVel, array[i].mass, array[i].imgFileName);
        }
    }
}
