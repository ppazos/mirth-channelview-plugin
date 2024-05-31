/*
 * Copyright 2021 Kaur Palang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kaurpalang.mirthpluginsample.client.panel;

import com.kaurpalang.mirthpluginsample.client.draw.*;
import com.kaurpalang.mirthpluginsample.shared.MyConstants;
import com.mirth.connect.client.ui.AbstractChannelTabPanel;
// import com.mirth.connect.client.ui.components.MirthCheckBox;
// import com.mirth.connect.client.ui.components.MirthPasswordField;
import com.mirth.connect.client.ui.components.MirthTextArea;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import java.util.List;

import com.mirth.connect.model.Channel;
import com.mirth.connect.model.Connector;
import com.mirth.connect.model.Rule;
import com.mirth.connect.model.Step;

public class ChannelTabPanel extends AbstractChannelTabPanel { // extends JPanel

   /**
    * Create component variables
    */
   // private JLabel forcefulLabel;
   // private MirthCheckBox forcefulCheckbox;

   private JLabel channelLabel;
   private MirthTextArea channelTextArea;

   // private JLabel remotePasswordLabel;
   // private MirthPasswordField remotePasswordField;

   // private JButton testRemoteButton;

   public ChannelTabPanel() {
      // The name of our tab in the Settings menu
      super();
      initComponents();
   }

   private void initComponents() {
      setBackground(Color.WHITE);
      setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "[]", "[]")); // layout, column, row

      /**
      * Instantiate component variables
      */
      // forcefulLabel = new JLabel("Force push:");
      // forcefulCheckbox = new MirthCheckBox();
      // forcefulLabel.setToolTipText("This is a\nmultiline tooltip ;)");
      // forcefulCheckbox.setBackground(Color.WHITE);

      channelLabel = new JLabel("Channel architecture:");
      channelTextArea = new MirthTextArea();
      channelTextArea.setLineWrap(true); // https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html
      channelTextArea.setColumns(120);

      // remotePasswordLabel = new JLabel("Password:");
      // remotePasswordField = new MirthPasswordField();

      // testRemoteButton = new JButton("Do a thing button");

      /**
      * Create the base container for our components
      */
      JPanel contentPanel = new JPanel(); // TODO: make it scrollable
      contentPanel.setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "[]12[][grow]", "")); // layout, column, row
      contentPanel.setBackground(Color.WHITE);
      contentPanel.setBorder(
         BorderFactory.createTitledBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(204, 204, 204)),
            "Channel View",
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font("Tahoma", 1, 11)
         )
      );

      /**
      * Add our components to the base container
      */
      //deetsPanel.add(forcefulLabel, "right");
      //deetsPanel.add(forcefulCheckbox);
      contentPanel.add(channelLabel, "newline");
      contentPanel.add(channelTextArea, "newline, sx, growx");
      // deetsPanel.add(remotePasswordLabel, "newline, right");
      // deetsPanel.add(remotePasswordField, "w 168!");
      // deetsPanel.add(testRemoteButton, "newline");


      //CanvasPanel canvas = new CanvasPanel();
      
      
      // TEST: https://github.com/shayan4shayan/java-GraphDraw/tree/master
      //create an object
      GraphDraw draw = new GraphDraw();
      draw.setBackground(Color.DARK_GRAY);
      
      //create a node
      Node node = new Node();
      node.setDisplayName("n1");
      node.setColor(Color.BLUE);
      node.setPosition(new Node.Position(10, 10));
      draw.addNode(node);
      
      //create another node
      Node node1 = new Node();
      node1.setDisplayName("n2");
      node1.setColor(Color.GREEN);
      node1.setPosition(new Node.Position(100, 20));
      draw.addNode(node1);
      
      //create an edge
      Edge edge = new Edge(node, node1);
      edge.setFocussed(true);
      edge.setColor(Color.MAGENTA);
      edge.setWeight(2);
      draw.addEdge(edge);
      
      //displaying graph
      draw.setSize(contentPanel.getWidth(), 400);
      draw.setVisible(true);
      
      contentPanel.add(draw, "newline, grow");
      
      
      
      
      //contentPanel.add(canvas, "newline, growx");
      
      
      
      
      
      
      JScrollPane scrollPane = new JScrollPane(contentPanel);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      //scrollPane.setBounds(50, 30, 300, 50); // TODO: the scroll should be of the size of the container panel
      
      

      /**
      * Add base container to Mirth's more base-er container
      */
      add(scrollPane, "grow");
   }

   @Override
   public void load(Channel channel) {
      System.out.println("Hello from the CLIENT ChannelTabPanel.load()");

      String text = "";

      Connector source = channel.getSourceConnector();

      //   for (Rule rule: source.getFilter().getElements())
      //   {
      //     text += "(F)"+ rule.getName() + " " + rule.getOperator() + " -> ";
      //   }

      for (Step step: source.getTransformer().getElements())
      {
         text += "(T) "+ step.getName() + " -> \n";
      }

      //   List<Connector> destinations = channel.getDestinationConnectors();
      // TODO:

      channelTextArea.setText(text);
   }

   @Override
   public void save(Channel channel) {

   }
}
