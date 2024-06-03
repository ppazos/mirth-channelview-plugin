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

package com.cabolabs.channelview.client.panel;

import com.cabolabs.channelview.client.draw.*;
import com.cabolabs.channelview.shared.MyConstants;
import com.mirth.connect.client.ui.AbstractChannelTabPanel;
// import com.mirth.connect.client.ui.components.MirthCheckBox;
// import com.mirth.connect.client.ui.components.MirthPasswordField;
import com.mirth.connect.client.ui.components.MirthTextArea;
//import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import java.awt.*;

import java.util.List;

import com.mirth.connect.model.Channel;
import com.mirth.connect.model.Connector;
import com.mirth.connect.model.Rule;
import com.mirth.connect.model.Step;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.swing.mxGraphComponent;

public class ChannelTabPanel extends AbstractChannelTabPanel { // extends JPanel

   /**
    * Create component variables
    */
   // private JLabel forcefulLabel;
   // private MirthCheckBox forcefulCheckbox;

   private JLabel channelLabel;
   private MirthTextArea channelTextArea;
   
   // To avoid the text in the arrows
   class MyEdge extends DefaultEdge {
	    @Override
	    public String toString()
	    {
	    	return "";
	    }
	}

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
      //setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "[]", "[]")); // layout, column, row
 

      /**
      * Instantiate component variables
      */
      // forcefulLabel = new JLabel("Force push:");
      // forcefulCheckbox = new MirthCheckBox();
      // forcefulLabel.setToolTipText("This is a\nmultiline tooltip ;)");
      // forcefulCheckbox.setBackground(Color.WHITE);

      //channelLabel = new JLabel("Channel architecture:");
      //channelTextArea = new MirthTextArea();
      //channelTextArea.setLineWrap(true); // https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html
      //channelTextArea.setColumns(120);

      // remotePasswordLabel = new JLabel("Password:");
      // remotePasswordField = new MirthPasswordField();

      // testRemoteButton = new JButton("Do a thing button");

      /**
      * Create the base container for our components
      */
      JPanel contentPanel = new JPanel(); // TODO: make it scrollable
      //contentPanel.setLayout(new MigLayout("insets 12, novisualpadding, hidemode 3, fill, gap 6", "[]12[][grow]", "")); // layout, column, row
      contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
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
      
      contentPanel.add(channelLabel);
      //contentPanel.add(channelTextArea);

      
      
      // GRAPH
      // create a JGraphT graph
      ListenableGraph<String, DefaultEdge> g =
          new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));
      
      //Set<DefaultEdge> edges = g.edgeSet();

      // create a visualization using JGraph, via an adapter
      JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(g);
      
      //Dimension DEFAULT_SIZE = new Dimension(530, 320);

      //applet.setPreferredSize(DEFAULT_SIZE);
      mxGraphComponent component = new mxGraphComponent(jgxAdapter); // this is a scroll pane!
      
      
      JLabel lblNewLabel = new JLabel("New label");
      component.setColumnHeaderView(lblNewLabel);
	
      // this shows the background color!
      component.getViewport().setOpaque(true);
      component.getViewport().setBackground(Color.YELLOW);
		
      component.setConnectable(false);
      
      // DATA
      String v1 = "Trasnformer 1\nGet x and put y";
      String v2 = "Transformer 2\nGet z and put w";
      String v3 = "Transformer 3\nFormat datetime";
      String v4 = "Transformer 4\nJSON Stringify";

      // add some sample data (graph manipulated via JGraphX)
      g.addVertex(v1);
      g.addVertex(v2);
      g.addVertex(v3);
      g.addVertex(v4);

      g.addEdge(v1, v2, new MyEdge());
      g.addEdge(v2, v3, new MyEdge());
      g.addEdge(v3, v4, new MyEdge());
      
      
      String v5 = "Filter 1";
      String v6 = "Filter 2";
      
      g.addVertex(v5);
      g.addVertex(v6);
      
      g.addEdge(v5, v6, new MyEdge());
      // /DATA
      
      mxStackLayout layout = new mxStackLayout(jgxAdapter, true, 50, 10, 10, 0);
      layout.execute(jgxAdapter.getDefaultParent());
      
      // /GRAPH
      
      contentPanel.add(component);
      
      
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
