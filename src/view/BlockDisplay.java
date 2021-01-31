/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Block;

/**
 *
 * @author Pablo
 */
public interface BlockDisplay extends Block.Observer{
    public Block block();
    public void display(Block block);
    
}
