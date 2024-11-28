package org.firstinspires.ftc.teamcode.opmode.auto;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class FieldLocations {
    // red submersible locations
    public static final Pose2D redAscent = new Pose2D(DistanceUnit.INCH, -14.75, 0, AngleUnit.DEGREES, -90);
    public static final Pose2D redChamber = new Pose2D(DistanceUnit.INCH, 0, -24.125, AngleUnit.DEGREES, 0);

    // red bucket side locations
    public static final Pose2D redBucketStart = new Pose2D(DistanceUnit.INCH, -11.875, -58.75, AngleUnit.DEGREES, 90);
    public static final Pose2D redBucketBucket = new Pose2D(DistanceUnit.INCH, -64.875, -64.875, AngleUnit.DEGREES, 90);
    public static final Pose2D redBucketSpike1 = new Pose2D(DistanceUnit.INCH, -47.75, -25.625, AngleUnit.DEGREES, 0);
    public static final Pose2D redBucketSpike2 = new Pose2D(DistanceUnit.INCH, redBucketSpike1.getX(DistanceUnit.INCH) - 10, redBucketSpike1.getY(DistanceUnit.INCH), AngleUnit.DEGREES, redBucketSpike1.getHeading(AngleUnit.DEGREES));
    public static final Pose2D redBucketSpike3 = new Pose2D(DistanceUnit.INCH, redBucketSpike2.getX(DistanceUnit.INCH) - 10, redBucketSpike2.getY(DistanceUnit.INCH), AngleUnit.DEGREES, redBucketSpike2.getHeading(AngleUnit.DEGREES));

    // red specimen side locations
    public static final Pose2D redSpecimenStart = new Pose2D(DistanceUnit.INCH, -redBucketStart.getX(DistanceUnit.INCH), redBucketStart.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -redBucketStart.getHeading(AngleUnit.DEGREES));
    public static final Pose2D redSpecimenObservation = new Pose2D(DistanceUnit.INCH, 58.5, -64.25, AngleUnit.DEGREES, -90);
    public static final Pose2D redSpecimenSpike1 = new Pose2D(DistanceUnit.INCH, -redBucketSpike1.getX(DistanceUnit.INCH), redBucketSpike1.getY(DistanceUnit.INCH), AngleUnit.DEGREES, redBucketSpike1.getHeading(AngleUnit.DEGREES));
    public static final Pose2D redSpecimenSpike2 = new Pose2D(DistanceUnit.INCH, redSpecimenSpike1.getX(DistanceUnit.INCH) + 10, redSpecimenSpike1.getY(DistanceUnit.INCH), AngleUnit.DEGREES, redSpecimenSpike1.getHeading(AngleUnit.DEGREES));
    public static final Pose2D redSpecimenSpike3 = new Pose2D(DistanceUnit.INCH, redSpecimenSpike2.getX(DistanceUnit.INCH) + 10, redSpecimenSpike2.getY(DistanceUnit.INCH), AngleUnit.DEGREES, redSpecimenSpike2.getHeading(AngleUnit.DEGREES));

    // blue submersible locations
    public static final Pose2D blueAscent = new Pose2D(DistanceUnit.INCH, -redAscent.getX(DistanceUnit.INCH), -redAscent.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -redAscent.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueChamber = new Pose2D(DistanceUnit.INCH, -redChamber.getX(DistanceUnit.INCH), -redChamber.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -redChamber.getHeading(AngleUnit.DEGREES));

    // blue bucket side locations
    public static final Pose2D blueBucketStart = new Pose2D(DistanceUnit.INCH, -redBucketStart.getX(DistanceUnit.INCH), -redBucketStart.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -redBucketStart.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueBucketBucket = new Pose2D(DistanceUnit.INCH, -redBucketBucket.getX(DistanceUnit.INCH), -redBucketBucket.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -redBucketBucket.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueBucketSpike1 = new Pose2D(DistanceUnit.INCH, -redBucketSpike1.getX(DistanceUnit.INCH), -redBucketSpike1.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -redBucketSpike1.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueBucketSpike2 = new Pose2D(DistanceUnit.INCH, blueBucketSpike1.getX(DistanceUnit.INCH) + 10, blueBucketSpike1.getY(DistanceUnit.INCH), AngleUnit.DEGREES, blueBucketSpike1.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueBucketSpike3 = new Pose2D(DistanceUnit.INCH, blueBucketSpike2.getX(DistanceUnit.INCH) + 10, blueBucketSpike2.getY(DistanceUnit.INCH), AngleUnit.DEGREES, blueBucketSpike2.getHeading(AngleUnit.DEGREES));

    // blue specimen side locations
    public static final Pose2D blueSpecimenStart = new Pose2D(DistanceUnit.INCH, -blueBucketStart.getX(DistanceUnit.INCH), blueBucketStart.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -blueBucketStart.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueSpecimenObservation = new Pose2D(DistanceUnit.INCH, -redSpecimenObservation.getX(DistanceUnit.INCH), -redSpecimenObservation.getY(DistanceUnit.INCH), AngleUnit.DEGREES, -redSpecimenObservation.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueSpecimenSpike1 = new Pose2D(DistanceUnit.INCH, -blueBucketSpike1.getX(DistanceUnit.INCH), blueBucketSpike1.getY(DistanceUnit.INCH), AngleUnit.DEGREES, blueBucketSpike1.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueSpecimenSpike2 = new Pose2D(DistanceUnit.INCH, blueSpecimenSpike1.getX(DistanceUnit.INCH) - 10, blueSpecimenSpike1.getY(DistanceUnit.INCH), AngleUnit.DEGREES, blueSpecimenSpike1.getHeading(AngleUnit.DEGREES));
    public static final Pose2D blueSpecimenSpike3 = new Pose2D(DistanceUnit.INCH, blueSpecimenSpike2.getX(DistanceUnit.INCH) - 10, blueSpecimenSpike2.getY(DistanceUnit.INCH), AngleUnit.DEGREES, blueSpecimenSpike2.getHeading(AngleUnit.DEGREES));
}
