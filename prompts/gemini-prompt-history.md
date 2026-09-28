### Branch: task/add-order-button

I'm still finalizing the UI, so let's start there. I need to add a "PLace Order" button at the bottom of
the screen, within the Column in UserButtonGroup(). Here's a screenshot of the UI in various states so you
can get an understanding of the layout: [file-path] and the specs for the
"Place Order" button: [file-path]

### (From Agent) Next Step

Since we are in Explore Mode (thinking and design only), we have two paths:

1. Continue exploring: Clarify the status indicator, color theming constants, or callback signatures.
2. Move to implementation: Create an OpenSpec change proposal (e.g. /opsx:propose add-place-order-button), or confirm and scaffold
   the change to implement the UI.

How would you like to proceed?

────────────────────────────────────────────────────────────
> Choice 1. Since we're exploring the UI finalization, let's clarify the status indicator, set up colors and typography and figure out
the callback signatures.

### (From Agent) Questions to Align On 

1. Indicator Layout: Should OrderStatusIndicator reserve a fixed height when IDLE (to prevent the button/content from shifting     
   slightly when the status appears), or is conditional display fine?
2. Debug Button Interactivity: In the final screen, do users tap the "Place Order" button to generate clicks, or should the        
   "Clicks" and "Requests Started" rows still be clickable independently for testing?
3. Scope for Capture: Does this cover the design exploration to your satisfaction, and would you like to capture this as an        
   OpenSpec change proposal next?

────────────────────────────────────────────────────────────
> 1. Yes, reserve a fixed height to avoid any flashing/ visible height adjustments on a user's screen. 
> 2. I just realized my mistake. "Clicks" and "Requests" should be non-interactive text fields instead of buttons. The only interaction should happen through clicking
     the "Place Order" button. The text fields should simply reflect the status of the click - whether it's a valid request or a click
     that was debounced. With this change, onClickCount and onRequestCount should be ints and not callbacks. We should also edit the name
     of the Composable to better reflect the activity happening in that area of the screen. For more technical context, the full technical
     requirements are available in the file: [file-path]
> 3. Before closing the exploration stage, let's update the plans with the newest updates from answer 2.
