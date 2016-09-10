package wm.server.web.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wm.lib.WmDb;
import wm.lib.WmRmi;
import wm.lib.model.Game;
import wm.lib.model.Tip;
import wm.server.db.WmDbMySqlImpl;
import wm.server.logic.WmLogicImpl;

public class ServiceServlet extends HttpServlet {
	
	private static final WmDb wmdb = new WmDbMySqlImpl();
	private static final WmRmi wmService = new WmLogicImpl(wmdb);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/json");
		res.setStatus(HttpServletResponse.SC_OK);
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		try {
			// create Json Object
			JSONObject json = new JSONObject();
			
			JSONObject jsonGames = new JSONObject();
			List<Game> games = wmService.getGames();
			JSONArray array = new JSONArray();
			for (Game g : games) {
				JSONObject gameObj = new JSONObject();
				gameObj.put("country1", g.getTeam1Id());
				gameObj.put("country2", g.getTeam2Id());
				gameObj.put("goalsCountry1", g.getGoal1());
				gameObj.put("goalsCountry2", g.getGoal2());
				array.put(gameObj);
			}
			jsonGames.put("Games", array);
			
			JSONObject highscoreJson = new JSONObject();
			List<Tip> tips = wmService.getTips();
			array = new JSONArray();
			for (Tip t : tips) {
				JSONObject tipObj = new JSONObject();
				tipObj.put("username", t.getUserId());
				tipObj.put("points", wmService.evaluateTip(t.getId()));
				array.put(tipObj);
			}
			jsonGames.put("Highscores", array);
			
			// finally output the json string
			out.print(json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{ 'error': '" + e.getMessage() + "' }");
		}
		out.flush();
		out.close();
	}

}
