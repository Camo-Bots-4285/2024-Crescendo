//Copyed from team 4829
//
//


package frc.robot.commands;


import com.pathplanner.lib.commands.PathPlannerAuto;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
/**
 * This class is for the 5 Ball Auto Command
 */
public class FeedToShot extends SequentialCommandGroup {
   // public class FeedToShot extends repeatingSequenceCommandGroup{
  public FeedToShot(ShooterSubsystem m_shooter, ArmPivotSubsystem m_ArmPivotSubsystem,
      IntakeSubsystem m_intake, ShooterFeederSubsystem m_shooterFeeder,SwerveBase m_swerveBase) {

    addCommands(
        
        // 1. intake till linebreck is broken
        new ParallelCommandGroup(
            new ArmPivotFarHumanFeeder(m_ArmPivotSubsystem),
            new ShootingFarHumanFeeder(m_shooter)
        ).end(LineBreak.HasNote == true),
        
        // 2. Then continues intaking while moving to shot position
        new ParallelCommandGroup(
            new ShootingWithoutCameras(m_ArmPivotSubsystem),
            new ShootingFarHumanFeeder(m_shooter)
        ).withTimeout(0.5),

        // 3. Contine Moving to fire postion and revs shooter
        new ParallelCommandGroup(
            new ShootingWithoutCameras(m_ArmPivotSubsystem),
            new ShooterCrossField(m_shooter)
        ).withTimeout(0.5),
       
        // 4. Shots Note
        new ParallelCommandGroup(
            new ShootingWithoutCameras(m_ArmPivotSubsystem),
            new ShooterCrossField(m_shooter),
            new ShooterFeederFire(m_shooterFeeder)
        ).withTimeout(0.25)
    );
  }
}