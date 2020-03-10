package resources;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenCaptureUtility

{

	public boolean areImagesEqual(String baseline, String screenshots)
	{
		BufferedImage imgBaseline = null;
		BufferedImage imgScreenshots = null;
		try {
		imgBaseline = ImageIO.read(new File(System.getProperty("user.dir")+ "\\src\\images\\baseline\\" + baseline + ".png"));
		
		imgScreenshots = ImageIO.read(new File(System.getProperty("user.dir")+ "\\src\\images\\screenshots\\" + screenshots + ".png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
			ImageDiff diff = new ImageDiffer().makeDiff(imgBaseline, imgScreenshots);
			boolean isDifferent = diff.hasDiff();
			
			if (isDifferent)
			{
				BufferedImage diffImage= diff.getMarkedImage();
				try
				{
				ImageIO.write(diffImage,  "png", new File(System.getProperty("user.dir")+ "\\src\\images\\diffImages\\" + baseline + ".png"));
				} catch (IOException e1) {}
					
		}
		
		
		return !isDifferent;
		}
		
	
	public void takePageScreenShot(WebDriver driver, String name) 
	
	{
		Screenshot screen = new AShot().takeScreenshot(driver);
		BufferedImage b1 = screen.getImage();
		
		File file = new File(System.getProperty("user.dir")+ "\\src\\images\\screenshots\\" + name + ".png");
		try {
			ImageIO.write(b1, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void takePageScreenShotImprove(WebDriver driver, String name) 
	
	{
		Screenshot screen = new AShot().shootingStrategy(ShootingStrategies.viewportPasting( 100)).takeScreenshot(driver);
		BufferedImage b1 = screen.getImage();
		
		File file = new File(System.getProperty("user.dir")+ "\\src\\images\\screenshots\\" + name + ".png");
		try {
			ImageIO.write(b1, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void takeElementsScreenshot(WebDriver driver, String name, WebElement element)
	{
		Screenshot screen = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element); //.takeScreenshot(driver);
		BufferedImage b1 = screen.getImage();
		
		File file = new File(System.getProperty("user.dir")+ "\\src\\images\\screenshots\\" + name + ".png");
		try {
			ImageIO.write(b1, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void prepareBaseLineImages(WebDriver driver, String name) {
		Screenshot screen = new AShot().shootingStrategy(ShootingStrategies.viewportPasting( 100)).takeScreenshot(driver);
		BufferedImage b1 = screen.getImage();
		
		File file = new File(System.getProperty("user.dir")+ "\\src\\images\\baseline\\" + name + ".png");
		try {
			ImageIO.write(b1, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
