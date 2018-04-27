package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.blocks.item.IMetaBlockName;
import com.mrcrayfish.furniture.tileentity.TileEntityCouch;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCouchNormal extends BlockCouch implements IMetaBlockName
{
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(COLOUR, 15 - Math.max(0, meta));
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		TileEntity te = super.createTileEntity(world, state);
		if (te instanceof TileEntityCouch) {
			((TileEntityCouch) te).setColour(state.getValue(COLOUR));
		}
		return te;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		drops.add(new ItemStack(this, 1, 15 - Math.max(0, state.getValue(COLOUR))));
	}

	@Override
	public void getSubBlocks(CreativeTabs item, NonNullList<ItemStack> items)
	{
		for (int i = 0; i < EnumDyeColor.values().length; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public boolean isSpecial()
	{
		return false;
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		int metadata = stack.getMetadata();
		if (metadata < 0 || metadata >= EnumDyeColor.values().length) {
			return EnumDyeColor.WHITE.getDyeColorName();
		}
		return EnumDyeColor.values()[metadata].getDyeColorName();
	}
}
