package com.alexalien.modularmissiles.common;

import com.alexalien.modularmissiles.common.blocks.Blocks;
import com.alexalien.modularmissiles.common.world.WorldGenerationSetup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alexalien.modularmissiles.common.items.Items;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Modularmissiles.MODID)






public class Modularmissiles
{
    // Directly reference a log4j logger.



	public static final String MODID = "modularmissiles";
	public static final String MOD_NAME = "Modular Missiles";
	
	
    private static final Logger LOGGER = LogManager.getLogger();



    public static ModularmissilesGroup ModularDefaultGroup = new ModularmissilesGroup("Modular Missiles");
    public static ModularmissilesGroup ModularModularGroup = new ModularmissilesGroup("Missile Parts");


    public Modularmissiles() {
    	
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Blocks.BLOCKSREGISTRY.register(modEventBus);
    	Items.ITEMSREGISTRY.register(modEventBus);



    	
        // Register the setup method for modloading

        // Register the enqueueIMC method for modloading
       // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
       // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in




        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        //MinecraftForge.EVENT_BUS.register(this);
        
        
        
        
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        WorldGenerationSetup.runSetup();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("icbmoverhaul", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
       // @SubscribeEvent
        /*public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }

         */
    }
}