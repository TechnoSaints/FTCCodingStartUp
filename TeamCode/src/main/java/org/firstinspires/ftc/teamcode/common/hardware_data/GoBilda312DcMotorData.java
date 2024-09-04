package org.firstinspires.ftc.teamcode.common.hardware_data;
public class GoBilda312DcMotorData {
    public static double ticksPerMotorRev = 28;
    public static double gearRatio = 19.2;
    public static double wheelDiameterInches = 96.0/25.4;
    public static double liftPulleyDiameterInches = 44.0/25.4;
    public static double ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
    public static double wheelCircumferenceInches = wheelDiameterInches * Math.PI;
    public static double liftPulleyCircumferenceInches = liftPulleyDiameterInches * Math.PI;
    public static double wheelTicksPerInch = ticksPerGearboxRev/wheelCircumferenceInches;
    public static double liftPulleyticksPerInch = ticksPerGearboxRev/liftPulleyCircumferenceInches;
    public static double maxMotorRpm = 5900;
    public static double maxMotorRps = maxMotorRpm / 60.0;
    public static double maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
}
