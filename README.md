# RainyHills Web Application project
This application was developed for the interview task.
It takes an array of surface points as an input, and calculates the volume of water which remained after the rain, in units.

Built with JDK 1.8 and Primefaces 5.3

## Project description
The main class of this project is [Surface.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/model/Surface.java) class.
It presents a surface with a water on it and it has several methods like fill some water, wipe the water, get surface curve, get water and get total water amount.
It also has factory method for generating random surface object by parameters of length, min height and max height.

To calculate the water on a curved surface the so called **Vessel Method** was developed using TDD approach in this project.
It's presented by [WFMFullVessel.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/model/waterfill/impl/WFMFullVessel.java)
class ('full' because it calculates the maximum amount of the water which can "fully" fill the surface)
and implements the [WaterFillMethod.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/model/waterfill/WaterFillMethod.java) interface.
Detailed description and complexity evaluation of the algorithm is presented in its Javadoc.

While using TDD approach two [SurfaceDrawer.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/draw/SurfaceDrawer.java) implementations were developed
for visualization of a surface with and without water in console for the help purpose in developing:
[VerticalConsoleSurfaceDrawer.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/draw/impl/VerticalConsoleSurfaceDrawer.java) - draws surface in console vertically;
[HorizontalConsoleSurfaceDrawer.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/draw/impl/HorizontalConsoleSurfaceDrawer.java) - draws surface in console horizontally.

Two other implementations of water-on-surface calculation algorithms ([WFMFullTower.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/model/waterfill/impl/WFMFullTower.java)
and [WFMFullTowerOptimized.java](https://github.com/ail-man/rainy-hills/blob/master/src/main/java/com/ail/crxmarkets/model/waterfill/impl/WFMFullTowerOptimized.java)
) were used as well just for comparison purpose. They were taken from the Internet. 

All algorithms were optimized to support negative values of the surface heights (pits).

## Testing algorithm speed
There was developed some tests that you can use to check the calculation speed of each of the algorithms.
See [WFMFullVesselTest.java](https://github.com/ail-man/rainy-hills/blob/master/src/test/java/com/ail/crxmarkets/model/waterfill/impl/WFMFullVesselTest.java),
[WFMFullTowerTest.java](https://github.com/ail-man/rainy-hills/blob/master/src/test/java/com/ail/crxmarkets/model/waterfill/impl/WFMFullTowerTest.java)
and [WFMFullTowerOptimizedTest.java](https://github.com/ail-man/rainy-hills/blob/master/src/test/java/com/ail/crxmarkets/model/waterfill/impl/WFMFullTowerOptimizedTest.java) classes.

## Run project with maven
To run project with embedded GlassFish 4.1.1 container execute
```
mvn clean package embedded-glassfish:run
```
Then open in browser [http://localhost:8080/RainyHills/](http://localhost:8080/RainyHills/)