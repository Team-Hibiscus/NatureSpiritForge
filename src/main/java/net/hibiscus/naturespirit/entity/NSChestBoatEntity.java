package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class NSChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public NSChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NSChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(NSEntityTypes.NS_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        switch (getNSVariant()) {
            case REDWOOD -> NSBlocks.REDWOOD.getBoatItem().get();
            case SUGI -> NSBlocks.SUGI.getBoatItem().get();
            case WISTERIA -> NSBlocks.WISTERIA.getBoatItem().get();
            case FIR -> NSBlocks.FIR.getBoatItem().get();
            case WILLOW -> NSBlocks.WILLOW.getBoatItem().get();
            case ASPEN -> NSBlocks.ASPEN.getBoatItem().get();
            case MAPLE -> NSBlocks.MAPLE.getBoatItem().get();
            case CYPRESS -> NSBlocks.CYPRESS.getBoatItem().get();
            case OLIVE -> NSBlocks.OLIVE.getBoatItem().get();
            case JOSHUA -> NSBlocks.JOSHUA.getBoatItem().get();
            case GHAF -> NSBlocks.GHAF.getBoatItem().get();
            case PALO_VERDE -> NSBlocks.PALO_VERDE.getBoatItem().get();
            case COCONUT -> NSBlocks.COCONUT.getBoatItem().get();
            case CEDAR -> NSBlocks.CEDAR.getBoatItem().get();
            case LARCH -> NSBlocks.LARCH.getBoatItem().get();
            case MAHOGANY -> NSBlocks.MAHOGANY.getBoatItem().get();
            case SAXAUL -> NSBlocks.SAXAUL.getBoatItem().get();
        }
        return super.getDropItem();
    }

    public void setVariant(NSBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, NSBoatEntity.Type.REDWOOD.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getNSVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(NSBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public NSBoatEntity.Type getNSVariant() {
        return NSBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
