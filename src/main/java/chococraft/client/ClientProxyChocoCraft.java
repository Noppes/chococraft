// <copyright file="ClientProxyChocoCraft.java">
// Copyright (c) 2012 All Right Reserved, http://chococraft.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2012-10-25</date>
// <summary>ChocoCraft Client proxy</summary>

package chococraft.client;

import chococraft.common.ModChocoCraft;
import chococraft.common.config.ChocoCraftBlocks;
import chococraft.common.config.ChocoCraftItems;
import chococraft.common.config.Constants;
import chococraft.common.proxy.CommonProxyChocoCraft;
import chococraft.common.entities.EntityChicobo;
import chococraft.common.entities.EntityChocoboRideable;
import chococraft.common.entities.RiderActionState;
import chococraft.common.entities.colours.*;
import chococraft.common.entities.models.ModelChicobo;
import chococraft.common.entities.models.ModelChocobo;
import chococraft.common.entities.models.RenderChicobo;
import chococraft.common.entities.models.RenderChocobo;
import chococraft.common.network.PacketRegistry;
import chococraft.common.network.serverSide.ChocoboUpdateRiderActionState;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxyChocoCraft extends CommonProxyChocoCraft
{
    @Override
    public void registerRenderThings()
    {
    }

	@Override
    public void registerRenderInformation()
    {
		RenderManager manager = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityChocoboYellow.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboGreen.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboBlue.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboWhite.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboBlack.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboGold.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboPink.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboRed.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChocoboPurple.class, new RenderChocobo(manager, new ModelChocobo(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityChicobo.class, new RenderChicobo(manager, new ModelChicobo(), 0.5F));

		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocopediaItem, "Chocopedia");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboFeatherItem, "Chocobo_Feather");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboSaddleItem, "Chocobo_Saddle");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlPicklesItem, "Gysahl_Pickles");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlPicklesRawItem, "Gysahl_Raw_Pickles");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboLegCookedItem, "Cooked_Chocobo_Leg");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboLegRawItem, "Raw_Chocobo_Leg");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguiseBootsItem, "Chocodisguise_Boots");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguiseLegsItem, "Chocodisguise_Legs");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguisePlateItem, "Chocodisguise_Body");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguiseHelmetItem, "Chocodisguise_Helmet");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.purpleChocoboEggItem, "Purple_Chocobo_Egg");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboWhistleItem, "Chocobo_Whistle");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlCakeItem, "Gysahl Cake");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlRedItem, "Red_Gysahl");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlPinkItem, "Pink_Gysahl");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlGoldenItem, "Golden_Gysahl");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlLoverlyItem, "Loverly_Gysahl");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlSeedsItem, "Gysahl_Seeds");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboSaddleBagsItem, "Chocobo_Saddle_Bags");
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboPackBagsItem, "Chocobo_Pack_Bags");

		ModChocoCraft.proxy.registerBlockTexture(ChocoCraftBlocks.gysahlGreenBlock, "gysahlGreenBlock");
		ModChocoCraft.proxy.registerBlockTexture(ChocoCraftBlocks.gysahlStemBlock, "gysahlStemBlock");
		ModChocoCraft.proxy.registerBlockTexture(ChocoCraftBlocks.strawBlock, "strawBlock");
    }

    @Override
    public void registerBlockTexture(final Block block, final String blockName) {
        registerBlockTexture(block, blockName, 0);
    }

    @Override
    public void registerBlockTexture(final Block block, final String blockName, int meta) {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Constants.TCC_MODID + ":" + blockName,"inventory"));
    }

	@Override
	public void registerItemTexture(final Item item, final String itemName) {
		registerItemTexture(item, itemName, 0);
	}

    @Override
    public void registerItemTexture(final Item item, final String itemName, int meta) {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(item, meta, new ModelResourceLocation(Constants.TCC_MODID + ":" + itemName,"inventory"));
    }
    
    @Override
    public int addArmor(String armor)
    {
//        return RenderingRegistry.addNewArmourRendererPrefix(armor);
		//TODO 1.8
return -1;
    }
    
    @Override
    public void registerEventListener()
    {
		super.registerEventListener();
    }
    
    @Override
    public void updateRiderActionState(EntityChocoboRideable chocobo, Entity entity)
    {
        if (chocobo.worldObj.isRemote)
        {
            chocobo.setRiderActionState(this.getRiderActionState(entity));
            
        	if(chocobo.riderActionState.isChanged())
        	{
        		ChocoboUpdateRiderActionState packet = new ChocoboUpdateRiderActionState(chocobo, entity);
        		PacketRegistry.INSTANCE.sendToServer(packet);
        	}
        	
            chocobo.riderActionState.resetChanged();
        }
    }
    
    @Override
	public RiderActionState getRiderActionState(Entity rider)
	{
    	RiderActionState ras = new RiderActionState();
    	if(rider instanceof EntityPlayerSP)
    	{
    		EntityPlayerSP riderSP = (EntityPlayerSP)rider;
    		ras.setMoveForward(riderSP.movementInput.moveForward);
    		ras.setMoveStrafe(riderSP.movementInput.moveStrafe);
    		ras.setJump(riderSP.movementInput.jump);
    		ras.setSneak(riderSP.movementInput.sneak);
    	}
		return ras;
	}

}