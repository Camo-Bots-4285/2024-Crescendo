// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ArmPivotConstants;
import frc.robot.Constants.LineBreakConstants;
import frc.robot.subsystems.ArmPivotSubsystem;
import frc.robot.subsystems.LEDSubsystem;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LineBreak;
import frc.robot.Constants.LEDConstants;
/** An example command that uses an example subsystem. */
public class HasNote extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final LineBreak m_subsystem;
 private static boolean HasNote;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public HasNote(LineBreak subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (LineBreakConstants.DIO_BOTTOM_SENSOR == 0){
        HasNote = true;
    }
        else{
        HasNote = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   
  }

 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (HasNote == true){
        return false;
    }
    else{
        return true;
    }
  }
}
