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
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxyChocoCraft extends CommonProxyChocoCraft
{
    @Override
    public void registerRenderThings()
    {
    	//MinecraftForgeClient.preloadTexture(Constants.CHOCOBO_ITEM_TEXTURES);
        //MinecraftForgeClient.preloadTexture(Constants.CHOCOBO_ARMOUR_TEXTURES_1);
        //MinecraftForgeClient.preloadTexture(Constants.CHOCOBO_ARMOUR_TEXTURES_2);
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

		ModelBakery.addVariantName(Item.getItemFromBlock(ChocoCraftBlocks.strawBlock), Constants.TCC_MODID + ":" + ChocoCraftBlocks.strawBlock.getUnlocalizedName().substring(5));
		registerItemTexture(Item.getItemFromBlock(ChocoCraftBlocks.strawBlock), Constants.TCC_MODID + ":" + ChocoCraftBlocks.strawBlock.getUnlocalizedName().substring(5));

		ModelBakery.addVariantName(ChocoCraftItems.chocoboFeatherItem, Constants.TCC_MODID + ":" + ChocoCraftBlocks.strawBlock.getUnlocalizedName().substring(5));
		registerItemTexture(ChocoCraftItems.chocoboFeatherItem, Constants.TCC_MODID + ":" + ChocoCraftBlocks.strawBlock.getUnlocalizedName().substring(5));

    }

	public void registerItemTexture(Item item, final String location) {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		if(renderItem != null)
		{
			renderItem.getItemModelMesher().register(item, new ItemMeshDefinition() {
				public ModelResourceLocation getModelLocation(ItemStack stack)
				{
					return new ModelResourceLocation(location);
				}
			});
		}
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