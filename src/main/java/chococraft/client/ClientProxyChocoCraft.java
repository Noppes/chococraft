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
import chococraft.common.items.helper.ISimpleTextureHelper;
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
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLLog;

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

		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocopediaItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboFeatherItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboSaddleItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlPicklesItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlPicklesRawItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboLegCookedItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboLegRawItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguiseBootsItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguiseLegsItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguisePlateItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoDisguiseHelmetItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.purpleChocoboEggItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboWhistleItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlCakeItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlRedItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlPinkItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlGoldenItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlLoverlyItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.gysahlSeedsItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboSaddleBagsItem);
		ModChocoCraft.proxy.registerItemTexture(ChocoCraftItems.chocoboPackBagsItem);

		ModChocoCraft.proxy.registerBlockTexture(ChocoCraftBlocks.gysahlGreenBlock);
		ModChocoCraft.proxy.registerBlockTexture(ChocoCraftBlocks.gysahlStemBlock);
		ModChocoCraft.proxy.registerBlockTexture(ChocoCraftBlocks.strawBlock);
    }

	@Override
	public void registerBlockTexture(final Block block) {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		if(renderItem == null)
			return;

		if(!(block instanceof ISimpleTextureHelper))
		{
			FMLLog.severe("Block "+block.getClass().getName()+" does not extend ISimpleTextureHelper");
			return;
		}

		Item blockItem = Item.getItemFromBlock(block);
		renderItem.getItemModelMesher().register(blockItem, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return ((ISimpleTextureHelper)block).getDefaultModelLocation();
			}
		});
	}

	@Override
	public void registerItemTexture(final Item item) {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		if(renderItem == null)
			return;

		if(!(item instanceof ISimpleTextureHelper))
		{
			FMLLog.severe("Block "+item.getClass().getName()+" does not extend ISimpleTextureHelper");
			return;
		}

		renderItem.getItemModelMesher().register(item, new ItemMeshDefinition() {
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return ((ISimpleTextureHelper)item).getDefaultModelLocation();
			}
		});
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