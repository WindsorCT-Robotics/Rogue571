/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * @author Gabe
 */
public class ArmSolenoid extends SubsystemBase {
  private final String name = "Arm Pin";
  private final Solenoid armSolenoid = new Solenoid(1);

  /**
   * Raises and lowers arm pin
   */
  public ArmSolenoid() {
    addChild("Arm Pin Solenoid", armSolenoid);

    ShuffleboardLayout subsystemLayout = Shuffleboard.getTab("Subsystems")
        .getLayout("Arm Pin", BuiltInLayouts.kList);

    subsystemLayout.addBoolean("Arm Pin Solenoid", () -> armSolenoid.get());
  }

  @Override
  public String getName() {
    return name;
  }

 @Override
 public void periodic() {
   super.periodic();
 }

 public void lockArm(){
   armSolenoid.set(true);
 }

 public void unlockArm() {
   armSolenoid.set(false);
 }

 public boolean isArmLocked() {
   return armSolenoid.get();
 }
}
