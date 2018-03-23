ngApp.factory("utils", [
function() {

	function createD3Tree(url, wrap) {
		var width = 500, height = 500;

		var tree = d3.layout.tree().size([width, height - 200]).separation(function(a, b) {
			return (a.parent == b.parent ? 1 : 2) / a.depth;
		});

		var diagonal = d3.svg.diagonal().projection(function(d) {
			return [d.y, d.x];
		});

		var svg = d3.select(wrap).append("svg").attr("width", width).attr("height", height).append("g").attr("transform", "translate(50,0)");

		d3.json(url, function(error, root) {

			var nodes = tree.nodes(root);
			var links = tree.links(nodes);

			console.log(nodes);
			console.log(links);

			var link = svg.selectAll(".link").data(links).enter().append("path").attr("class", "link").attr("d", diagonal);

			var node = svg.selectAll(".node").data(nodes).enter().append("g").attr("class", "node").attr("transform", function(d) {
				return "translate(" + d.y + "," + d.x + ")";
			});

			node.append("circle").attr("r", 2.5);
			node.append("text").attr("dx", function(d) {
				return d.children ? -8 : 8;
			}).attr("dy", 3).style("text-anchor", function(d) {
				return d.children ? "end" : "start";
			}).text(function(d) {
				return d.name;
			});
		});

	}
	
	function isEmptyObject(e) {  
		var t;  
		for (t in e)  
			return !1;  
		return !0  
	}

	var utils = {
		createD3Tree : createD3Tree,
		isEmptyObject : isEmptyObject
	};
	return utils;
}]);
