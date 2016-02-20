package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

public class FtcOpModeRegister implements OpModeRegister {
  public void register(OpModeManager manager) {


    manager.register("NullOp", NullOp.class);
    manager.register ("Tele-OP-COMP", TeleOpPractice.class);
    manager.register ("BlueTimeAuto1", BlueTimeAuto1.class);
    manager.register ("RedTimeAuto1", RedTimeAuto1.class);
    manager.register ("BlueTimeAuto2", BlueTimeAuto2.class);
    manager.register ("RedTimeAuto2", RedTimeAuto2.class);
    manager.register ("DoNothingAuto", DoNothingAuto.class);
  }
}
