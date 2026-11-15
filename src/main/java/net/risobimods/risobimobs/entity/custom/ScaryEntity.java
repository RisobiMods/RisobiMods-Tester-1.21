package net.risobimods.risobimobs.entity.custom;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.level.NoteBlockEvent;
import net.risobimods.risobimobs.RisobiMobs;

public class ScaryEntity extends Monster implements RangedAttackMob {

    private static final TargetingConditions TARGETING = TargetingConditions.forCombat().range(10.0D);
    private LivingEntity target = null;
    private boolean hasSeenPlayer = false;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int unseenTicks = 0;


    public ScaryEntity(EntityType<? extends ScaryEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {


        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

        //this.goalSelector.addGoal(1, new TemptGoal(this, 1, stack -> stack.is(Tags.Items.CROPS_WHEAT), false));

        //this.goalSelector.addGoal(2, new FloatGoal(this));

        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 20.0F, 1f));

        //this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 24d);
    }


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();

        }

        Player nearestPlayer = this.level().getNearestPlayer(TargetingConditions.forCombat(), this);

        if (nearestPlayer != null) {
            float distance = this.distanceTo(nearestPlayer);
            if (distance <= 10) {
                RisobiMobs.LOGGER.warn("Too Close");
                discard();


            }

        }

        if (getTarget() != null) {



            target = getTarget();
            hasSeenPlayer = true;
            unseenTicks = 0;
            RisobiMobs.LOGGER.info("Scary Entity Target; {}", target.getDisplayName());
        }
        else if (hasSeenPlayer && target != null) {

            boolean canSeeEntity = target.hasLineOfSight(this);
            boolean isLookingAtEntity = false;




             if (canSeeEntity) {

                Vec3 look = target.getLookAngle();
                Vec3 direction = this.getEyePosition()
                        .subtract(target.getEyePosition())
                        .normalize();

                double dot = look.dot(direction);
                isLookingAtEntity = dot > 0.00;

            }

            if (isLookingAtEntity) {
                unseenTicks = 0;
            } else {
                unseenTicks++;

                //if (target.hasLineOfSight(this) == false) {
                //if (hasLineOfSight(target) == false) {


            }
            if (unseenTicks >= 20) {
                RisobiMobs.LOGGER.warn("Goodbye");
                discard();
            }
        }

    }
    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {

    }
}
