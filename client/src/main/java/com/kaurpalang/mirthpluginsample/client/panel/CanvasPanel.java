package com.kaurpalang.mirthpluginsample.client.panel;

import javax.swing.*; // JPanel

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class CanvasPanel extends JPanel {

   private static final int RECT_X = 20;
   private static final int RECT_Y = RECT_X;
   private static final int RECT_WIDTH = 100;
   private static final int RECT_HEIGHT = RECT_WIDTH;

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      // draw the rectangle here
      g.setColor(Color.RED);
      //g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
      g.fillRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
      
   }

   @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(this.getRootPane().getWidth() - 60, RECT_HEIGHT + 2 * RECT_Y);
   }
}