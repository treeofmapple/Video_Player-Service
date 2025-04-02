package com.tom.service.storage.openfeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tom.service.storage.model.Tag;
import com.tom.service.storage.model.UserComments;
import com.tom.service.storage.model.Video;

@FeignClient(name = "videoclient", url = "http://localhost:8000/api/v1/videos")
public interface VideoOpenFeign {

	/* VIDEO */
	/* GET MAPPING */
	
	@GetMapping("/video/all")
	List<Video> getAllVideos();

	@GetMapping("/title/{title}")
	Video getVideoByTitle(@PathVariable String title);
	
	@GetMapping("/popular")
	List<Video> getPopularVideos();
	
	@GetMapping("/latest")
	List<Video> getByLastestVideos();

	/* POST MAPPING */
	
	@PostMapping("/video/add")
	Video addVideo(@RequestBody Video video);
	
	@PostMapping("/video/add/list")
	List<Video> addVideos(@RequestBody List<Video> video);
	
	/* PUT MAPPING */
	
	@PutMapping("/change/video/description")
	Video addDescription(@RequestBody String name);
	
	@PutMapping("/video/change/url")
	Video addVideoURL(@RequestBody String name);
	
	/* DELETE MAPPING */
	
	@DeleteMapping("/video/delete")
	Video deleteVideo(@RequestBody String title);
	
	@DeleteMapping("/video/delete/list")
	List<Video> deleteVideos(@RequestBody List<String> title); //dev
	
	
	/* TAG */
	/* GET MAPPING */
	
	@GetMapping("/tag/{name}")
	Tag getTag(@PathVariable String name);
	
	@GetMapping("/tag/all")
	List<Tag> getAllTags();
	
	/* POST MAPPING */
	
	@PostMapping("/tag/add")
	Tag addTag(@RequestBody Tag tag);
	
	@PostMapping("/tag/add/list")
	List<Tag> addMultipleTags(@RequestBody List<Tag> tag);

	/* PUT MAPPING */
	
	@PutMapping("/tag/change/name")
	Tag changeTagName(@RequestBody String name);
	
	/* DELETE MAPPING */
	
	@DeleteMapping("/tag/delete")
	Tag deleteTag(@RequestBody String title);
	
	@DeleteMapping("/tag/delete/list")
	List<Tag> deleteTags(@RequestBody List<String> title); //dev
	
	/* UserComments */ // Future Update
	/* GET MAPPING */
	
	@GetMapping("/comment/{user}") // get by user
	UserComments getUserComment(@PathVariable String user);
	
	@GetMapping("/comment/all") // title  
	List<UserComments> getAllComments(@RequestBody String title);
	
	@GetMapping("/comment/title/{user}") // get all by user on title
	List<UserComments> getAllUserCommentOnTitle(@PathVariable String user, @RequestBody String title); //dev
	
	@GetMapping("/comment/all/{user}") // get all the comemnts made by the user
	List<UserComments> getAllUserComment(@PathVariable String user); //dev
	
	/* POST MAPPING */
	
	@PostMapping("/comment/post") // post a comment on video
	UserComments commentPost(@RequestBody String UserCommentRequest);
	
	/* PUT MAPPING */
	
	@PutMapping("/comment/edit") // edit a comment on video 
	UserComments commentEdit(@RequestBody String UserCommentRequest);
	
	/* DELETE MAPPING */

	@DeleteMapping("/comment/delete") // delete a comment on a video
	UserComments commentDelete(@RequestBody String title, @RequestBody String UserCommentRequest); // only a user can delete its own
	
	@DeleteMapping("/comment/dev/delete")
	UserComments commentDeleteDev(@RequestBody String title, @RequestBody String UserCommentRequest); // dev, admin can delete every user comment
	
	@DeleteMapping("/comment/delete/all") // delete all coments on video
	UserComments commentDeleteAll(@RequestBody String title); // dev
	
	@DeleteMapping("/comment/delete/{user}") // delete all users comment
	UserComments commentUserDeleteAll(@PathVariable String user); // dev
	
}
