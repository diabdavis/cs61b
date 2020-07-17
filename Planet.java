
public class Planet
{
    /** Describing all the instance variables that define the Planet class */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** Gravitational constant */
    public static final double g_cap = 6.67e-11;

    /** Planet constructors */
    public Planet(double xP, double yP, double xV, double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p)
    {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** Method that calculates the distance between two planets */
    public double calcDistance(Planet p)
    {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    /** Method that calculates the force exerted on the planet calling this method
    by the planet given as an argument */
    public double calcForceExertedBy(Planet p)
    {
        double r = this.calcDistance(p);
        double force = g_cap * this.mass * p.mass / (r * r);
        return force;
    }

    /** Method that calculates the force in the x direction exerted on the planet
    calling this method by the planet given as an argument */
    public double calcForceExertedByX(Planet p)
    {
        double force = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        double force_x = force * dx / r;
        return force_x;
    }

    /** Method that calculates the force in the y direction exerted on the planet
    calling this method by the planet given as an argument */
    public double calcForceExertedByY(Planet p)
    {
        double force = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        double force_y = force * dy / r;
        return force_y;
    }

    /** Method that calculates the net force in the x direction exerted on the planet
    calling this method by the of array planets given as an argument */
    public double calcNetForceExertedByX(Planet[] p)
    {
        double netforce_x = 0;
        for (int i = 0; i < p.length; i++)
        {
            if (this.equals(p[i]))
            {
                ;
            }
            else
            {
                netforce_x = netforce_x + this.calcForceExertedByX(p[i]);
            }
        }
        return netforce_x;
    }

    /** Method that calculates the net force in the y direction exerted on the planet
    calling this method by the of array planets given as an argument */
    public double calcNetForceExertedByY(Planet[] p)
    {
        double netforce_y = 0;
        for (int i = 0; i < p.length; i++)
        {
            if (this.equals(p[i]))
            {
                ;
            }
            else
            {
                netforce_y = netforce_y + this.calcForceExertedByY(p[i]);
            }
        }
        return netforce_y;
    }

    /** Method that takes in an applied Force and time it was applied then
    adjusts the planet's velocity and position accordingly */
    public void update(double dt, double fx, double fy)
    {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    /** Method that draws a planet */
    public void draw()
    {
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
        StdDraw.show();
    }
}

