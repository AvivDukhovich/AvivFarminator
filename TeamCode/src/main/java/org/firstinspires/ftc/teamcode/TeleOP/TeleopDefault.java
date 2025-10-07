package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.Utils.GlobalData;

@TeleOp
public class TeleopDefault extends CommandOpMode {

    private GamepadEx gamepadEx1, gamepadEx2;

    private BarnRobot farminator;

    private static final double SLOW_MODE_TRIGGER_THRESHOLD = 0.05;

    private GlobalData.Alliance teamColor = GlobalData.Alliance.BLUE;


    @Override
    public void initialize() {

        // ------------------------
        // Initialize Robot Systems
        // ------------------------
        farminator = BarnRobot.getInstance();
        farminator.initBarnRobotSystems(hardwareMap, gamepad1, gamepad2, teamColor);

        // ------------------------
        // Initialize Gamepads
        // ------------------------
        gamepadEx1 = farminator.gamepadEx1;
        gamepadEx2 = farminator.gamepadEx2;


        // ------------------------
        // Button Mappings
        // ------------------------

        // Reset heading with X
        gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(() -> farminator.drive.resetHeading());

        // Y for transfer
         farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                 .whenPressed(farminator.transfer.transferCommand());

         farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                 .toggleWhenPressed(farminator.drive.alignToTag());


    }

    @Override
    public void run() {
        super.run();
        farminator.limelight.displayTelemetry();
        telemetry.update();
    }
}
