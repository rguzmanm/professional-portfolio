# RPA Challenge

It is a simple process to solve the [RPA Challenge](https://rpachallenge.com/). The goal of this challenge is to create a workflow that will input data from a spreadsheet to a form in which the fields change their position on the screen every time it is submitted.

## Prerequisites
You only need to have Google Chrome installed on your computer to run this automation.

## UiPath
If you only want to run the automation, use the [RPA.Challenge.1.0.1.nupkg](https://github.com/rguzmanm/professional-portfolio/blob/master/RPA%20Projects/RPA%20Challenge/RPA%20Challenge%20-%20UiPath/RPA.Challenge.1.0.1.nupkg) package, publish it to your local orchestrator, and run it. No other configuration is required.

You can download the source code from [here](https://github.com/rguzmanm/professional-portfolio/tree/master/RPA%20Projects/RPA%20Challenge/RPA%20Challenge%20-%20UiPath/Code).

## Automation Anywhere
Import the [RPA Challenge.zip](https://github.com/rguzmanm/professional-portfolio/blob/master/RPA%20Projects/RPA%20Challenge/RPA%20Challenge%20-%20AA360/RPA%20Challenge.zip) file into your control room. Go to the Main_RPAChallenge task bot in the RPA Challenge folder and run the automation.

## Blue Prism
Import the [RPA Challenge V1.0.1.bprelease](https://github.com/rguzmanm/professional-portfolio/tree/master/RPA%20Projects/RPA%20Challenge/RPA%20Challenge%20-%20Blue%20Prism) to our local control room and run the RPAChallenge process. No other configuration is required.

## Workfusion
1. Download the source code from [here](https://github.com/rguzmanm/professional-portfolio/tree/master/RPA%20Projects/RPA%20Challenge/RPA%20Challenge%20-%20Workfusion/rpachallenge).
2. Move the downloaded files to your Workfusion Workspace folder. 
3. Import the project into your local studio using the "Import Existing Maven Projects" option.
4. Use the "clean install" instruction in the "rpachallenge" root folder
5. Use the "bundle:import" instruction in the "rpachallenge-package" folder to publish the Business Process into the Control Tower
