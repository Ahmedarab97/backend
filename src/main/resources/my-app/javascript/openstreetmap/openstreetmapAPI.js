import { fromLonLat } from 'ol/proj';

export async function getCoordinatenVanOpenStreetMap(postcode) {
    const geocodingUrl = `https://nominatim.openstreetmap.org/search?format=json&q=${postcode}`;
    const response = await fetch(geocodingUrl);
    const data = await response.json();
    let coordinates = null;
    if (data.length !== 0) {
        const result = data[0];
        coordinates = fromLonLat([parseFloat(result.lon), parseFloat(result.lat)]);
        return coordinates
    }
}

// export async function getBoundingBoxForNeighborhood(neighborhoodName) {
//     const query = `[out:json];
//     area[name="${neighborhoodName}"]->.searchArea;
//     (
//       relation["boundary"="administrative"]["admin_level"="10"](area.searchArea);
//     );
//     out geom;`;
//
//     const url = 'https://overpass-api.de/api/interpreter';
//
//     const response = await fetch(url, {
//         method: 'POST',
//         body: query,
//     });
//
//     if (response.ok) {
//         const data = await response.json();
//         if (data.elements.length > 0) {
//             console.log(data);
//             const coordinates = data.elements[0].bounds;
//
//             // Vind de min en max lon en lat
//             let minLon = coordinates.minlon;
//             let maxLon = coordinates.maxlon;
//             let minLat = coordinates.minlat;
//             let maxLat = coordinates.maxlat;
//
//             // for (const coord of coordinates) {
//             //     const lon = coord[0];
//             //     const lat = coord[1];
//             //
//             //     if (lon < minLon) minLon = lon;
//             //     if (lon > maxLon) maxLon = lon;
//             //     if (lat < minLat) minLat = lat;
//             //     if (lat > maxLat) maxLat = lat;
//             // }
//
//             // console.log(minLon)
//             // console.log(maxLon)
//             // console.log(minLat)
//             // console.log(maxLat)
//
//             return {
//                 minLon,
//                 maxLon,
//                 minLat,
//                 maxLat,
//             };
//         }
//     } else {
//         console.error('Fout bij het ophalen van gegevens:', response.statusText);
//     }
// }