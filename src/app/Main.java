package app;

import control.Command;
import control.DownCommand;
import control.LeftCommand;
import control.RightCommand;
import control.UpCommand;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import model.Block;
import view.BlockDisplay;

/**
 *
 * @author Pablo
 */
public class Main extends JFrame {
    
    private Block block;
    private BlockDisplay blockDs;
    private Map<String, Command> commands = createCommands();
    
    public static void main(String[] args) {
        new Main().execute();
    }
    
    public Main() {
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(),BorderLayout.SOUTH);
    }
    
    public void execute() {
        this.block = new Block();
        this.blockDs.display(block);
        this.block.register(blockDs);
        
        this.setVisible(true);
    }
    
    private BlockPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel();
        this.blockDs = blockPanel;
        return blockPanel;
    }
    
    private Map<String,Command> createCommands(){
        Map<String, Command> commands = new HashMap<>();
        
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        commands.put("D", new DownCommand(block));
        commands.put("U", new UpCommand(block));
                
        return commands;
    }
    
    private JMenuBar toolbar() {
        JMenuBar res = new JMenuBar();
        res.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        res.add(button("L"));
        res.add(button("R"));
        res.add(button("D"));
        res.add(button("U"));
                
        return res;
    }
    
    private JButton button(String command) {
        JButton button = new JButton(command);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                commands.get(command).execute();
            }
        });
        return button;
    }
}

