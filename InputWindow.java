package codebreaker;

/*
* I have created input and output Windows applications which are a custom made java windows designed to take input from user or display some information as output to the user. 
* They have been designed and written by using Java Swing Components like Jarea, Jlabel, Jbutton, Jframe etc. 
* I have also used notifyAll() method to make an application wait for users input.
*
* - You may use this Class in your application for non commercial use if you wish but you have to keep this comment below :
*
* @author Michal Switala
* Copyright © 2021 belongs solely to Michal Switala. 
* You can reach me out on : https://github.com/michaelPro89
*/

/*****************************************************************
 * Author: Michal Switala 
 * Version: 4 
 * References:    
 * https://www.youtube.com/watch?v=FLkOX4Eez6o - shows how to create window in
 * Java using the simpliest way
 *
 * https://www.youtube.com/watch?v=5KyYAsYvLjs&list=PLS1QulWo1RIa
 * UGP446_pWLgTZPiFizEMq&index=3
 *
 * https://www.youtube.com/watch?v=rT-J-0nGyzU&t=507s
 *
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/
 * components/TopLevelDemoProject/src/components/TopLevelDemo.java - How to
 * create window with menu and yellow backgorund
 *
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html#GlassPaneDemo
 * - Here you can find all java examples explaining everything with download
 *
 * https://docs.oracle.com/javase/8/javafx/layout-tutorial/style_css.htm#JFXLY161
 * - styling Layout Panes with CSS
 *
 * https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html - How to
 * use Actions, for ex. how to create new actions when you hit a key on keyboard
 *
 * https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html - How to
 * use key bindings on your keyboard to do something, for example how to bind
 * key ENTER and set an action for it
 ****************************************************************/

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;


class InputWindow implements ActionListener {

    
    // Fields of data

    //creating top-level container JFrame

    JFrame window;
    //add 3 labels and make them centered inside your window

    JLabel labelTop;
    JLabel labelTop2;
    JLabel labelTop3;
    JLabel labelBottom;
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel scorePanel;
    //The GridBagConstraints specifies where a component's display area should be located 

    GridBagConstraints c;
    //Creates new Font object with default settings

    Font newFont;
    //create textarea
    JTextArea textArea;
   //create new buttons
    JButton buttonEnter;

    JButton buttonExit;
    //Object to receive user input

    JTextField userInput;
    //Create JScrollPane
    
    JScrollPane scrollbar;
    
    //Borders
    Border myBorder;

    private boolean playerInputEmpty = true;

    //Change colors here to apply them to the window

    final Color BACKGROUND_COLOR = new Color(51,153,255);
    final Color TEXTAREA_COLOR = new Color(242, 242, 242);
    final Color BUTTON_COLOR = new Color(51, 102, 255);
    final Color TEXT_COLOR = new Color(255, 255, 255);

    // Constructors:


    /**
     * This constructor have all objects/classes instantiated and setup already inside.
     * 
     * @param title title to be displayed on window
     */
    InputWindow(String title) {
 
        //Instantiate new objects
        this.window = new JFrame(title);
        ImageIcon icon = createImageIcon("codebreaker.png", "just an img");
        this.labelTop = new JLabel(icon);
        this.labelTop2 = new JLabel("Some text", JLabel.CENTER);
        this.labelTop3 = new JLabel("Some text");
        this.labelBottom = new JLabel("Do you want to play a game ? ", JLabel.CENTER);
        this.mainPanel = new JPanel(new GridBagLayout());
        this.buttonPanel = new JPanel(new BorderLayout());
        this.scorePanel = new JPanel(new BorderLayout());
        this.c = new GridBagConstraints();
        this.newFont = new Font("Century Gothic", Font.BOLD, 29);
        this.buttonEnter = new JButton("Enter");
        this.buttonExit = new JButton("Exit");
        this.userInput = new JTextField("enter your quess here", 16);
        this.myBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        this.textArea = new JTextArea();
        this.scrollbar = new JScrollPane(textArea);

        //Here we instantiate new actions used when we press ENTER or ESCAPE on keyboard
        Action enterPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // method "doClick"  does work as if you have just clicked the button
                buttonEnter.doClick(20);

            }
        };

        Action escapePressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buttonExit.doClick(20);

            }
        };
        // "enterPressed" are javax.swing.Action objects
        // This code adds key "ENTER"  and name of action "enterPressed" to the inputMap
        // "WHEN_IN_FOCUSED_WINDOW" is used to react to pressed key no matter where the focus is
        this.buttonEnter.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
        // This code adds key "ESCAPE"  and name of action "enterPressed" to the inputMap
        this.buttonExit.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escapePressed");

        
        //Here we add our action to action map
        this.buttonEnter.getActionMap().put("enterPressed", enterPressed);
        this.buttonExit.getActionMap().put("escapePressed", escapePressed);

        //This code prevents key "SPACE" to react on Enter button and Exit button
        this.buttonEnter.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.buttonExit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

        //Here we set vertical bar to be displayed always and horizontal scrollbar never
        this.scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //adding action listeners and setting up names of action commands
        //so we will identify which button has been clicked and add proper code to it
        this.buttonEnter.addActionListener(this);
        this.buttonEnter.setActionCommand("Enter");
        this.buttonExit.addActionListener(this);
        this.buttonExit.setActionCommand("Exit");

        //Change settings of Components here
        this.textArea.setBorder(myBorder);
        this.userInput.setBorder(myBorder);
      
        
        this.buttonPanel.setPreferredSize(new Dimension(170, 26));
        this.scorePanel.setPreferredSize(new Dimension(190, 50));
        //Change settings of window here (JFrame)
        this.window.setSize(490, 600); // (width, height) 
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //changed background color of the panel
        this.mainPanel.setBackground(this.BACKGROUND_COLOR);
        this.buttonPanel.setBackground(this.BACKGROUND_COLOR);
        this.scorePanel.setBackground(this.BACKGROUND_COLOR);

        //Here change settings of textField
        this.textArea.setBackground(this.TEXTAREA_COLOR);
        //If we add a scrollbar to panel we then set size of our scrollbar instead of panel
        this.scrollbar.setPreferredSize(new Dimension(400, 260)); // (width, height)
        this.scrollbar.setVisible(true);
        //Makes textArea unable to edit 
        this.textArea.setEditable(false);
        //Makes textArea content will always fit inside textArea width
        this.textArea.setWrapStyleWord(true);
        this.textArea.setLineWrap(true);
        this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 16));

        //Change size of Buttons
        this.buttonEnter.setPreferredSize(new Dimension(75, 26));
        this.buttonEnter.setForeground(this.TEXT_COLOR);
        this.buttonEnter.setBackground(this.BUTTON_COLOR);

        // change font of the buttons
        this.buttonEnter.setFont(new Font("Century Gothic", Font.BOLD, 15));
        this.buttonExit.setFont(new Font("Century Gothic", Font.BOLD, 15));

        this.buttonExit.setPreferredSize(new Dimension(75, 26));
        this.buttonExit.setForeground(this.TEXT_COLOR);
        this.buttonExit.setBackground(this.BUTTON_COLOR);

        // turn off focus  and borders of the buttons
        this.buttonEnter.setBorderPainted(false);
        this.buttonExit.setBorderPainted(false);
        this.buttonEnter.setFocusPainted(false);
        this.buttonExit.setFocusPainted(false);
        
   
        this.c.weighty = 0.2;
        this.c.ipady = 4;
        this.c.gridwidth =2;
        // Make the component fill its display area entirely 
        //in this case labelTop, scrollbar and labelBottom
        this.c.fill = GridBagConstraints.BOTH;

        //Change grids before adding labels
        this.c.gridy = 0;
        this.mainPanel.add(labelTop, c);
        
        
        this.c.gridy = 2;
        this.mainPanel.add(scrollbar, c);
        

        this.c.gridy = 3;
        this.mainPanel.add(labelBottom, c);
        
        this.c.fill = GridBagConstraints.HORIZONTAL;
        this.c.gridy = 4;

        this.userInput.setPreferredSize(new Dimension(10, 20));
        this.mainPanel.add(userInput, c);
        

        this.labelBottom.setForeground(this.TEXT_COLOR);
        this.labelBottom.setFont(new Font("Century Gothic", Font.BOLD, 15));
        this.labelBottom.setText("Default text");    
        this.labelBottom.setPreferredSize(new Dimension(100, 40));

        this.labelTop2.setForeground(this.TEXT_COLOR);
        this.labelTop2.setFont(new Font("Century Gothic", Font.BOLD, 16));
        this.labelTop2.setPreferredSize(new Dimension(10, 20));

        this.labelTop3.setForeground(this.TEXT_COLOR);
        this.labelTop3.setFont(new Font("Century Gothic", Font.BOLD, 16));
        this.labelTop3.setPreferredSize(new Dimension(10, 20));
        
        this.labelTop.setPreferredSize(new Dimension(70, 60));
        this.labelTop.setFont(new Font("Century Gothic", Font.BOLD, 20));
        

        this.labelTop2.setPreferredSize(new Dimension(70, 40));
        this.labelTop2.setFont(new Font("Century Gothic", Font.BOLD, 16));

        this.labelTop3.setPreferredSize(new Dimension(70, 40));
        this.labelTop3.setFont(new Font("Century Gothic", Font.BOLD, 16));

        
        this.c.fill = GridBagConstraints.BASELINE;
        // create new panel and add buttons to it, then add this new panel2 to panel1
        // then add panel2 to panel with a Grid so we can center it all vertically
        this.buttonPanel.add(buttonEnter, BorderLayout.WEST);
        this.buttonPanel.add(buttonExit, BorderLayout.EAST);       // <-- adding buttons like this, will make them position from left to right by default
        //if you want to arrange positions of components  in JPanel
        //You have to add(new BorderLayout to the JPanel)
        this.c.gridy = 6;
        //Do not resize the component, in this case buttonPanel. 
        this.c.fill = GridBagConstraints.NONE;        
        this.mainPanel.add(buttonPanel, c);

        
        this.scorePanel.add(this.labelTop2, BorderLayout.WEST);
        this.scorePanel.add(this.labelTop3, BorderLayout.EAST);    
        this.c.gridy = 1;
        this.c.fill = GridBagConstraints.NONE;
        this.mainPanel.add(this.scorePanel, c); 
        
        //Add main panel to the frame
        this.window.getContentPane().add(mainPanel);
       
        //Displays our window (JFrame) in the center of display screen
        this.window.setLocationRelativeTo(null);
        this.window.setResizable(false);
        
   
    }

    
    
    
    /*********************************
    *
    * 
    *            Methods list
    *
    *
    * 
    *********************************/
     
    
     /**
     * @param e ActionEvent to be passed as a parameter
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //if you click button "Exit" prorgam will shutdown
        if ("Exit".equals(e.getActionCommand())) {

            System.exit(0);

            ///If you click or hit "Enter" it will do what's inside realeseTheLock synchronized method
        } else if ("Enter".equals(e.getActionCommand())) {

            realeseTheLock();

        }

    }

    /**
     * This method allows to set life's that's 
     * going to be displayed inside JLabel
     * 
     * @param lifes setup amount of life's to be displayed
     */
    public void setLifes(String lifes) {

        this.labelTop2.setText("Lifes : " + lifes);
    }

    /**
     * This method allows to set clue's that's
     * going to be displayed inside JLabel
     * 
     * @param clues setup amount of clue's to be displayed
     */
    public void setClues(String clues) {

        this.labelTop3.setText("Clues : " + clues);
    }

    /**
     * This method is synchronized and has wait(); method inside.
     * It is setting the message to be displayed to the user and is waiting
     * for user to insert an input into the JTextField.
     * 
     * @param message message to be displayed to the user
     * @return this method returns 'String input' with users input inside
     */
    public synchronized String waitForPlayerInput(String message) {
        labelBottom.setText(message);
        String input;

        while (this.playerInputEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        //Set to true to wait for the next incoming input
        this.playerInputEmpty = true;

        //When "released lock" do this...
        input = userInput.getText();

        return input;
    }

    /**
     * This method is using notifyAll(); method inside and is 
     * changing boolean playerInputEmpty to false when used
     * 
     */
    public synchronized void realeseTheLock() {
        // notify all threads that are waiting that there's been a change
        notifyAll();

        //Set to false to get out of the loop and do something after that...
        this.playerInputEmpty = false;

    }

   

    /**
     * This method is setting and adding amount of margin to be
     * added to the JTextArea. You must pass /r as String margin for
     * a single margin or /r/r for double margin.
     * 
     * @param margin amount of margin to be added to the textArea.
     */
    public void setMargin(String margin) {

        this.textArea.append(margin);
    }

    /**
     * This method adds one empty line to the textArea.
     *      
     */
    public void nextLine() {
        this.textArea.append("\n");
    }

    /**
     *This method is setting font of textArea to Red color.
     */
    public void setFontRed() {
        this.textArea.setForeground(new Color(255, 0, 0));
    }

    /**
     *This method is setting  font of textArea to Orange color.
     */
    public void setFontOrange() {
        this.textArea.setForeground(new Color(255, 165, 0));
    }

    /**
     *This method is setting  font of textArea to Yellow color.
     */
    public void setFontYellow() {
        this.textArea.setForeground(new Color(255, 255, 0));
    }

    /**
     *This method is setting  font of textArea to Green color.
     */
    public void setFontGreen() {
        this.textArea.setForeground(new Color(0, 128, 0));
    }

    /**
     * This method is setting font of textArea to Brown color.
     */
    public void setFontBrown() {
        this.textArea.setForeground(new Color(102, 51, 0));
    }

    /**
     *This method is setting font of textArea to Indigo color.
     */
    public void setFontIndigo() {
        this.textArea.setForeground(new Color(75, 0, 130));
    }

    /**
     * This method is setting font of textArea to Violet color.
     */
    public void setFontViolet() {
        this.textArea.setForeground(new Color(102, 0, 102));
    }

    /**
     *
     * @param time the length of time to sleep thread in this class in milliseconds 
     * For ex. pass int with value 60000 to pause for 1 minute.
     */
    
    public void pause(int time) {
        
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    /**
     *
     * @param title String that sets the title of this window to be displayed
     */
    public void windowTitle(String title) {
        this.window.setTitle(title);
    }

    /**
     * This method is showing up this Window
     */
    public void showWindow() {
        this.window.setVisible(true);
    }

    /**
     *
     * @param fontName the name of the new font to be set
     * @param fontSize the font size to be set
     */
    public void changeFont(String fontName, int fontSize) {

        this.textArea.setFont(new Font(fontName, Font.BOLD, fontSize));

    }

    /**
     * This method is clearing textArea
     */
    public void clearWindow() {
        this.textArea.setText(" ");
    }

    /**
     *
     * @param text text that's going to be added at the end of 
     * the textArea of the window.
     * @return
     */
    public String addText(String text) {

        this.textArea.append(text);

        return text;

    }

    /**
     *
     * @param text text thats going to be set on labelBottom
     * of the window.
     */
    public void addMessage(String text) {

        this.labelBottom.setText(text);

    }

    /**
     *
     * @param text text thats going to be set on labelTop2
     * of the window.
     */
    public void setTextLabel2(String text) {

        this.labelTop2.setText(text);
    }

    /**
     *
     * @param text text thats going to be set on labelTop3
     * of the window.
     */
    public void setTextLabel3(String text) {

        this.labelTop3.setText(text);
    }

    /**
     * This method is setting color of Window to red.
     */
    public void setColorRed() {
        this.mainPanel.setBackground(new Color(255, 0, 0));
        this.buttonPanel.setBackground(new Color(255, 0, 0));
        this.scorePanel.setBackground(new Color(255, 0, 0));
    }

    /**
     * This method is setting color of Window to orange.
     */
    public void setColorOrange() {
        this.mainPanel.setBackground(new Color(255, 165, 0));
        this.buttonPanel.setBackground(new Color(255, 165, 0));
        this.scorePanel.setBackground(new Color(255, 165, 0));
    }

    /**
     * This method is setting color of Window to Yellow.
     */
    public void setColorYellow() {
        this.mainPanel.setBackground(new Color(255, 255, 0));
        this.buttonPanel.setBackground(new Color(255, 255, 0));
        this.scorePanel.setBackground(new Color(255, 255, 0));
    }

    /**
     * This method is setting color of Window to Green.
     */
    public void setColorGreen() {
        this.mainPanel.setBackground(new Color(0, 128, 0));
        this.buttonPanel.setBackground(new Color(0, 128, 0));
        this.scorePanel.setBackground(new Color(0, 128, 0));
        
    }

    /**
     * This method is setting color of Window to Brown.
     */
    public void setColorBrown() {
        this.mainPanel.setBackground(new Color(102, 51, 0));
        this.buttonPanel.setBackground(new Color(102, 51, 0));
        this.scorePanel.setBackground(new Color(102, 51, 0));
        
    }

    /**
     * This method is setting color of Window to Indigo.
     */
    public void setColorIndigo() {
        this.mainPanel.setBackground(new Color(75, 0, 130));
        this.buttonPanel.setBackground(new Color(75, 0, 130));
        this.scorePanel.setBackground(new Color(75, 0, 130));
    }

    /**
     *
     */
    public void setColorViolet() {
        this.mainPanel.setBackground(new Color(102, 0, 102));
        this.buttonPanel.setBackground(new Color(102, 0, 102));
    }

    /**
     *
     */
    public void someVisualisation() {

        this.textArea.append("Loading... please wait.");

        for (int y = 0; y < 4; y++) {
            pause(10);
            setColorRed();
            pause(60);
            setColorOrange();
            pause(90);
            setColorYellow();
            pause(80);
            setColorGreen();
            pause(60);
            setColorBrown();
            pause(60);
            setColorIndigo();
            pause(20);
            setColorViolet();
            pause(10);
            setColorRed();
            pause(60);
            setColorOrange();
            pause(90);
            setColorYellow();
            pause(80);
            setColorGreen();
            pause(60);
            setColorBrown();
            pause(60);
            setColorIndigo();
            pause(20);
            setColorViolet();
        }

        this.textArea.setText(" ");
        //Set our main colors back after the loop   
        this.mainPanel.setBackground(this.BACKGROUND_COLOR);
        this.buttonPanel.setBackground(this.BACKGROUND_COLOR);

    }

    /* The "createImageIcon" method finds the specified file and returns an ImageIcon for that file, or null if that file couldn't be found. Here is a typicalimplementation: */

    /**
     *
     * @param path
     * @param description
     * @return
     */

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }

    }
}
