package org.firstinspires.ftc.teamcode.common.hardware_data;

public class GoBilda1620DcMotorData extends MotorData {
    public GoBilda1620DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 3.7;
        wheelDiameterInches = 96.0 / 25.4;
        liftPulleyDiameterInches = 44.0 / 25.4;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        wheelCircumferenceInches = wheelDiameterInches * Math.PI;
        liftPulleyCircumferenceInches = liftPulleyDiameterInches * Math.PI;
        wheelTicksPerInch = ticksPerGearboxRev / wheelCircumferenceInches;
        liftPulleyTicksPerInch = ticksPerGearboxRev / liftPulleyCircumferenceInches;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}



