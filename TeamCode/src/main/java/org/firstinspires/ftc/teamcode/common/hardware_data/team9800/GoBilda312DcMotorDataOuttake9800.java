package org.firstinspires.ftc.teamcode.common.hardware_data.team9800;

import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;

public class GoBilda312DcMotorDataOuttake9800 extends MotorData {
    public GoBilda312DcMotorDataOuttake9800() {
        ticksPerMotorRev = 28;
        gearRatio = 19.2;
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



